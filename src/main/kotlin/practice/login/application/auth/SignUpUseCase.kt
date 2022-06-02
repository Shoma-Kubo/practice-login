package practice.login.application.auth

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserHashedPassword
import practice.login.domain.user.UserPassword
import practice.login.domain.user.UserRepository
import practice.login.presentation.form.SignUpForm
import practice.login.utility.Utils.nullOnNotFound

@Service
class SignUpUseCase(
  private val userRepository: UserRepository
) {

  fun signUpForm(
    modelAndView: ModelAndView
  ): ModelAndView = modelAndView.apply {
    viewName = "auth/signUp"
    addObject("form", SignUpForm.new())
  }

  fun signUp(
    modelAndView: ModelAndView,
    signUpForm: SignUpForm
  ): ModelAndView {

    val accountName: UserAccountName = UserAccountName.of(signUpForm.accountName)
    val hashedPassword: UserHashedPassword = UserPassword.of(signUpForm.password).toHashedPassword()

    // Check if account name does not exist
    if (
      nullOnNotFound {
        userRepository.findByAccountName(accountName = accountName)
      } != null
    ) throw Exception()

    // Create new user
    val user = UserEntity.new(
      accountName = accountName,
      hashedPassword = hashedPassword
    )
    userRepository.insert(user)

    return modelAndView.apply {
      viewName = "auth/signUpComplete"
    }
  }
}