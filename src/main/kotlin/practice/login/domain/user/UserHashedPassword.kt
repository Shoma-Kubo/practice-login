package practice.login.domain.user

import org.seasar.doma.Domain
import practice.login.domain.common.ValueObject

@Domain(valueType = String::class, factoryMethod = "of")
data class UserHashedPassword private constructor(
  val value: String
) : ValueObject() {

  companion object {

    @JvmStatic
    fun of(value: String) = UserHashedPassword(value)
  }
}