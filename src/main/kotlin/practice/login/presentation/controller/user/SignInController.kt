package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import practice.login.application.auth.SignInUseCase
import practice.login.config.HttpAttributeConst
import practice.login.domain.user.UserId
import practice.login.presentation.form.SignInForm
import javax.servlet.http.HttpServletRequest
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
    request: HttpServletRequest
  ): ModelAndView = signInUseCase.signInForm(
    modelAndView = modelAndView,
    userId = request.getAttribute(HttpAttributeConst.USER_ID_NAME)?.let { UserId.of(it.toString()) }
  )

  @PostMapping("/sign-in")
  @ResponseStatus(HttpStatus.OK)
  fun signIn(
    modelAndView: ModelAndView,
    request: HttpServletRequest,
    response: HttpServletResponse,
    @Valid signInForm: SignInForm
  ): ModelAndView = signInUseCase.signIn(
    modelAndView = modelAndView,
    response = response,
    userId = request.getAttribute(HttpAttributeConst.USER_ID_NAME)?.let { UserId.of(it.toString()) },
    signInForm = signInForm
  )
}