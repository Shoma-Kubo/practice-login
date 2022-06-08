package practice.login.application.auth

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.application.jsonWebToken.JsonWebTokenService
import practice.login.domain.jsonWebToken.AuthTokenEntity
import practice.login.domain.jsonWebToken.RefreshTokenEntity
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId
import practice.login.domain.user.UserPassword
import practice.login.domain.user.UserRepository
import practice.login.presentation.form.SignInForm
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.http.HttpServletResponse

@Service
class SignInUseCase(
  private val userRepository: UserRepository,
  private val jsonWebTokenService: JsonWebTokenService
) {

  private fun ModelAndView.alreadySignedIn() = this.apply {
    viewName = "auth/signInComplete"
  }

  private fun ModelAndView.signInFailed(
    signInForm: SignInForm
  ) = this.apply {
    viewName = "auth/signIn"
    addObject("form", signInForm)
  }

  private fun isSignedIn(
    userId: UserId?
  ): Boolean =
    userId?.let { it ->
      userRepository.existByUserId(it)
    } == true

  fun signInForm(
    modelAndView: ModelAndView,
    userId: UserId?
  ): ModelAndView {
    
    if (isSignedIn(userId))
      return modelAndView.alreadySignedIn()

    return modelAndView.apply {
      viewName = "auth/signIn"
      addObject("form", SignInForm.new())
    }
  }

  fun signIn(
    modelAndView: ModelAndView,
    response: HttpServletResponse,
    userId: UserId?,
    signInForm: SignInForm
  ): ModelAndView {

    // Redirect if already signed in
    if (isSignedIn(userId))
      return modelAndView.alreadySignedIn()

    val accountName = UserAccountName.of(signInForm.accountName)
    val rawPassword = UserPassword.of(signInForm.password)

    nullOnNotFound {
      userRepository.findByAccountName(accountName)
      // If account name does exist
    }?.let { user ->

      // Sign in failed if incorrect password
      if (!rawPassword.isCorrect(user.hashedPassword))
        return modelAndView.signInFailed(signInForm)

      jsonWebTokenService.setAuthTokenToResponse(
        response = response,
        authTokenEntity = AuthTokenEntity.new(user.id)
      )
      jsonWebTokenService.setRefreshTokenToResponse(
        response = response,
        refreshTokenEntity = RefreshTokenEntity.new(user.id)
      )

      // Redirect if sign in succeeded
      return modelAndView.alreadySignedIn()
    }

    return modelAndView.signInFailed(signInForm)
  }
}