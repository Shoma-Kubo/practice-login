package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import practice.login.application.user.SignUpUseCase
import practice.login.presentation.requestBody.RequestBodySignUp
import practice.login.presentation.responseBody.ResponseBodyUser
import javax.validation.Valid

@RestController
@RequestMapping("/sign_up")
@Validated
class SignUpController(
  private val signUpUseCase: SignUpUseCase
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun signUp(
    @Valid @RequestBody requestBodySignUp: RequestBodySignUp
  ): ResponseBodyUser = signUpUseCase.signUp(
    requestBodySignUp = requestBodySignUp
  )
}