package practice.login.presentation.controller.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import practice.login.application.auth.UserUseCase
import practice.login.domain.user.UserAccountName

@RestController
@RequestMapping("/user")
class UserController(
  private val userUseCase: UserUseCase
) {

  @GetMapping("/{accountName}")
  @ResponseStatus(HttpStatus.OK)
  fun get(
    @PathVariable("accountName") accountName: UserAccountName
  ) = userUseCase.get(accountName)
}