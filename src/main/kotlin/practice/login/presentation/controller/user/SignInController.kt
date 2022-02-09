package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import practice.login.application.user.SignInUseCase
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import practice.login.presentation.requestBody.RequestBodySignIn
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping
@Validated
class SignInController(
  private val signInUseCase: SignInUseCase
) {

  @GetMapping("/is_signed_in")
  @ResponseStatus(HttpStatus.OK)
  fun isSignedIn(
    @CookieValue(value = CookieConst.SESSION_ID_NAME, required = false) sessionId: SessionId?
  ) = signInUseCase.isSignedIn(
    sessionId = sessionId
  )

  @PostMapping("/sign_in")
  @ResponseStatus(HttpStatus.OK)
  fun signIn(
    response: HttpServletResponse,
    @CookieValue(value = CookieConst.SESSION_ID_NAME, required = false) sessionId: SessionId?,
    @Valid @RequestBody requestBodySignIn: RequestBodySignIn
  ) = signInUseCase.signIn(
    response = response,
    sessionId = sessionId,
    requestBodySignIn = requestBodySignIn
  )
}