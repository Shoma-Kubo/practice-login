package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import practice.login.application.auth.SignInUseCase
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import practice.login.presentation.form.SignInForm
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping
@Validated
class SignInController(
  private val signInUseCase: SignInUseCase
) {

  @GetMapping("/sign-in")
  @ResponseStatus(HttpStatus.OK)
  fun signInForm(
    modelAndView: ModelAndView,
    @CookieValue(value = CookieConst.SESSION_ID_NAME, required = false) sessionId: SessionId?
  ): ModelAndView = signInUseCase.signInForm(
    modelAndView = modelAndView,
    sessionId = sessionId
  )

  @PostMapping("/sign-in")
  @ResponseStatus(HttpStatus.OK)
  fun signIn(
    modelAndView: ModelAndView,
    response: HttpServletResponse,
    @CookieValue(value = CookieConst.SESSION_ID_NAME, required = false) sessionId: SessionId?,
    @Valid signInForm: SignInForm
  ): ModelAndView = signInUseCase.signIn(
    modelAndView = modelAndView,
    response = response,
    sessionId = sessionId,
    signInForm = signInForm
  )
}