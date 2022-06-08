package practice.login.domain.jsonWebToken

import org.seasar.doma.Domain
import practice.login.config.CookieConst
import practice.login.domain.common.ValueObject
import practice.login.domain.user.UserId
import practice.login.utility.Utils.decodeFromBase64URLSafeString
import practice.login.utility.Utils.toBase64URLSafeString
import practice.login.utility.Utils.toJsonString
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

@Domain(valueType = String::class, factoryMethod = "of")
open class JsonWebToken constructor(
  open val value: String
) : ValueObject() {

  companion object {

    private const val HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}"
    private const val SIGNATURE_ALGORITHM = "HmacSHA256"
    private const val KEY = "gAaBb#PbhAkD2AnSJPzp55KvwfC8swLd"

    @JvmStatic
    fun of(value: String) = JsonWebToken(value)

    fun new(
      payload: Map<String, String>
    ): JsonWebToken {

      // Generate unsigned token
      val encodedHeader: String = HEADER.toBase64URLSafeString()
      val encodedPayload: String = payload.toJsonString().toBase64URLSafeString()
      val unsignedToken: String = "$encodedHeader.$encodedPayload"

      // Generate signature
      val signature = generateSignature(unsignedToken)

      val signedToken: String = "$unsignedToken.$signature"

      return JsonWebToken(signedToken)
    }

    // Generate Signature
    private fun generateSignature(
      token: String
    ): String {
      val mac = Mac.getInstance(SIGNATURE_ALGORITHM)
      mac.init(SecretKeySpec(KEY.toByteArray(), SIGNATURE_ALGORITHM))
      return mac.doFinal(token.toByteArray())
        .joinToString("") { String.format("%02x", it and 255.toByte()) }
    }
  }

  fun isValid(): Boolean =
    this.isSignatureValid() && this.hasNotExpired()

  fun isSignatureValid(): Boolean {

    val splitToken: List<String> = this.value.split(".")
    val unsignedToken = "${splitToken[0]}.${splitToken[1]}"

    return splitToken[2] == generateSignature(unsignedToken)
  }

  fun hasNotExpired(): Boolean =
    this.getExpireAt()?.hasExpired() == false

  fun getUserId(): UserId? {
    val payload: String = this.value.split(".")[1].decodeFromBase64URLSafeString()
    val match = Regex(""""${CookieConst.JSON_WEB_TOKEN_PAYLOAD_USER_ID}":"(.+?)"""").find(payload)
    return match?.groups?.let { group -> group[1]?.value?.let { UserId.of(it) } }
  }

  fun getExpireAt(): JsonWebTokenExpireAt? {
    val payload: String = this.value.split(".")[1].decodeFromBase64URLSafeString()
    val match = Regex(""""${CookieConst.JSON_WEB_TOKEN_PAYLOAD_EXPIRE_AT}":"(.+?)"""").find(payload)
    return match?.groups?.let { group -> group[1]?.value?.let { JsonWebTokenExpireAt.fromString(it) } }
  }
}