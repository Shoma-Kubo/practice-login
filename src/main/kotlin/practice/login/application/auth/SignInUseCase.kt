package practice.login.application.auth

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdEntity
import practice.login.domain.session.SessionIdRepository
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserPassword
import practice.login.domain.user.UserRepository
import practice.login.presentation.form.SignInForm
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.http.HttpServletResponse

@Service
class SignInUseCase(
  private val userRepository: UserRepository,
  private val sessionService: SessionService,
  private val sessionIdRepository: SessionIdRepository
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
    sessionId: SessionId?
  ): Boolean = sessionService.isSignedIn(sessionId) != null

  fun signInForm(
    modelAndView: ModelAndView,
    sessionId: SessionId?
  ): ModelAndView {

    if (isSignedIn(sessionId = sessionId))
      return modelAndView.alreadySignedIn()

    return modelAndView.apply {
      viewName = "auth/signIn"
      addObject("form", SignInForm.new())
    }
  }

  fun signIn(
    modelAndView: ModelAndView,
    response: HttpServletResponse,
    sessionId: SessionId?,
    signInForm: SignInForm
  ): ModelAndView {

    // Redirect if already signed in
    if (isSignedIn(sessionId))
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

      val sessionIdEntity: SessionIdEntity =
        sessionService.createSessionId(userId = user.id)

      sessionService.setSessionIdToResponse(
        response = response,
        sessionIdEntity = sessionIdEntity
      )

      sessionIdRepository.deleteByUserId(sessionIdEntity.userId)

      // Redirect if sign in succeeded
      return modelAndView.alreadySignedIn()
    }

    return modelAndView.signInFailed(signInForm)
  }
}