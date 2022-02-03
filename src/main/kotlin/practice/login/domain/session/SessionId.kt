package practice.login.domain.session

import org.seasar.doma.Domain
import practice.login.domain.common.ValueObject
import practice.login.utility.Utils.toBase64URLSafeString
import java.util.*

@Domain(valueType = String::class, factoryMethod = "of")
data class SessionId private constructor(
  val value: String
) : ValueObject() {

  companion object {

    @JvmStatic
    fun of(value: String) = SessionId(value)

    fun new() = SessionId(UUID.randomUUID().toBase64URLSafeString())
  }
}