package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import practice.login.application.auth.SignOutUseCase
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import javax.servlet.http.HttpServletResponse

@RestController
class SingOutController(
  private val signOutUseCase: SignOutUseCase
) {

  @PostMapping("/sign-out")
  @ResponseStatus(HttpStatus.OK)
  fun signIn(
    modelAndView: ModelAndView,
    response: HttpServletResponse,
    @CookieValue(value = CookieConst.SESSION_ID_NAME, required = false) sessionId: SessionId?,
  ): ModelAndView = signOutUseCase.signOut(
    modelAndView = modelAndView,
    response = response,
    sessionId = sessionId
  )
}