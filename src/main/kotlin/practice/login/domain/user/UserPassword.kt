package practice.login.domain.user

import org.seasar.doma.Domain
import org.springframework.security.crypto.bcrypt.BCrypt
import practice.login.domain.common.ValueObject

@Domain(valueType = String::class, factoryMethod = "of")
data class UserPassword private constructor(
  val value: String
) : ValueObject() {

  companion object {

    @JvmStatic
    fun of(value: String) = UserPassword(value)
  }

  fun toHashedPassword(): UserHashedPassword =
    UserHashedPassword.of(BCrypt.hashpw(this.value, BCrypt.gensalt()))

  fun isCorrect(
    hashedPassword: UserHashedPassword
  ): Boolean = BCrypt.checkpw(this.value, hashedPassword.value)
}