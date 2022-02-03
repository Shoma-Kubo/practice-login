package practice.login.application.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserPassword
import practice.login.domain.user.UserRepository
import practice.login.presentation.requestBody.RequestBodySignUp
import practice.login.presentation.responseBody.ResponseBodyUser
import practice.login.utility.Utils.nullOnNotFound

@Service
class SignUpUseCase(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) {

  fun signUp(
    requestBodySignUp: RequestBodySignUp
  ): ResponseBodyUser {

    val accountName = UserAccountName.of(requestBodySignUp.accountName)
    // TODO Encode password
    val encodedPassword =
      UserPassword.of(requestBodySignUp.password) // passwordEncoder.encode(requestBodySignUp.password)

    if (
      nullOnNotFound {
        userRepository.findByAccountName(accountName = accountName)
      } != null
    ) throw Exception()

    val user = UserEntity.new(
      accountName = accountName,
      password = encodedPassword
    )
    userRepository.insert(user)

    return ResponseBodyUser(
      accountName = accountName.value
    )
  }
}