package practice.login.domain.session

import org.seasar.doma.Domain
import practice.login.domain.common.ValueObject
import java.time.LocalDateTime

@Domain(valueType = LocalDateTime::class, factoryMethod = "of")
data class SessionIdExpireAt private constructor(
  val value: LocalDateTime
) : ValueObject() {

  companion object {

    @JvmStatic
    fun of(value: LocalDateTime) = SessionIdExpireAt(value)

    fun new() = SessionIdExpireAt(LocalDateTime.now().plusMinutes(60))
  }

  fun hasExpired() = this.value < LocalDateTime.now()
}