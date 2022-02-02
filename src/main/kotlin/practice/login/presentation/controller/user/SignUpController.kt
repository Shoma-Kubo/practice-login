package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import practice.login.application.user.SignUpService
import practice.login.presentation.requestBody.RequestBodySignUp
import practice.login.presentation.responseBody.ResponseBodyUser
import javax.validation.Valid

@RestController
@RequestMapping("/signup")
@Validated
class SignUpController(
  private val signUpService: SignUpService
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun create(
    @Valid @RequestBody requestBodySignUp: RequestBodySignUp
  ): ResponseBodyUser = signUpService.create(
    requestBodySignUp = requestBodySignUp
  )
}