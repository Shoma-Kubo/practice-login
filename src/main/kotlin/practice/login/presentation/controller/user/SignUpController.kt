package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import practice.login.application.auth.SignUpUseCase
import practice.login.presentation.form.SignUpForm
import javax.validation.Valid

@Controller
@RequestMapping("/sign-up")
@Validated
class SignUpController(
  private val signUpUseCase: SignUpUseCase
) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun singUpForm(
    modelAndView: ModelAndView
  ) = signUpUseCase.signUpForm(
    modelAndView
  )

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  fun signUp(
    modelAndView: ModelAndView,
    @Valid signUpForm: SignUpForm
  ): ModelAndView = signUpUseCase.signUp(
    modelAndView = modelAndView,
    signUpForm = signUpForm
  )
}