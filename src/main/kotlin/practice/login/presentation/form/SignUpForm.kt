package practice.login.presentation.form

import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserPassword

data class SignUpForm constructor(
  val accountName: String,
  val password: String
) {

  companion object {

    fun of(
      accountName: UserAccountName,
      password: UserPassword
    ) = SignUpForm(
      accountName = accountName.value,
      password = password.value
    )

    fun new() = SignUpForm(
      accountName = "",
      password = ""
    )
  }
}