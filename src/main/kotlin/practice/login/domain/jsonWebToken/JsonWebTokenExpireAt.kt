package practice.login.domain.jsonWebToken

import org.seasar.doma.Domain
import practice.login.domain.common.ValueObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Domain(valueType = LocalDateTime::class, factoryMethod = "of")
data class JsonWebTokenExpireAt private constructor(
  val value: LocalDateTime
) : ValueObject() {

  companion object {

    private val FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @JvmStatic
    fun of(value: LocalDateTime) = JsonWebTokenExpireAt(value)

    fun fromString(value: String) = JsonWebTokenExpireAt(LocalDateTime.parse(value, FORMAT))

    fun new(
      lifeMinutes: Long
    ) = JsonWebTokenExpireAt(LocalDateTime.now().plusMinutes(lifeMinutes))
  }

  fun hasExpired() = this.value < LocalDateTime.now()

  fun toFormattedString() = this.value.format(FORMAT)
}