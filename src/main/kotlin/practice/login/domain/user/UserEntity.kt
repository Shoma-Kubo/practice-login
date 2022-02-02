package practice.login.domain.user

import practice.login.utility.Utils.toBase64URLSafeString
import java.util.*

data class UserEntity private constructor(
  val id: String,
  val accountName: String,
  val password: String,
) {

  companion object {

    fun new(
      accountName: String,
      password: String
    ) = UserEntity(
      id = UUID.randomUUID().toBase64URLSafeString(),
      accountName = accountName,
      password = password,
    )

    fun of(
      id: String,
      accountName: String,
      password: String
    ) = UserEntity(
      id = id,
      accountName = accountName,
      password = password
    )
  }
}