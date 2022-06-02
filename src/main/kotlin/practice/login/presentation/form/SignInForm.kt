package practice.login.presentation.form

import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserPassword

data class SignInForm constructor(
  val accountName: String,
  val password: String
) {

  companion object {

    fun of(
      accountName: UserAccountName,
      password: UserPassword
    ) = SignInForm(
      accountName = accountName.value,
      password = ""
    )

    fun new() = SignInForm(
      accountName = "",
      password = ""
    )
  }
}