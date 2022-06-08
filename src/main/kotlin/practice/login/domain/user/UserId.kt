package practice.login.domain.user

import org.seasar.doma.Domain
import practice.login.domain.common.ValueObject
import practice.login.utility.Utils.toBase64URLSafeString
import java.util.*

@Domain(valueType = String::class, factoryMethod = "of")
data class UserId private constructor(
  val value: String
) : ValueObject() {

  companion object {

    @JvmStatic
    fun of(value: String) = UserId(value)

    fun new() = UserId(UUID.randomUUID().toString().toBase64URLSafeString())
  }
}