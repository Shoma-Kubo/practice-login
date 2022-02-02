package practice.login.application.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserRepository
import practice.login.presentation.requestBody.RequestBodySignUp
import practice.login.presentation.responseBody.ResponseBodyUser
import practice.login.utility.Utils.nullOnNotFound

@Service
class SignUpService(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) {

  fun create(
    requestBodySignUp: RequestBodySignUp
  ): ResponseBodyUser {

    val accountName: String = requestBodySignUp.accountName
    val encodedPassword: String = passwordEncoder.encode(requestBodySignUp.password)

    if (
      nullOnNotFound {
        userRepository.findByAccountName(accountName = accountName)
      } != null
    ) throw Exception()

    val user = UserEntity.new(
      accountName = requestBodySignUp.accountName,
      password = encodedPassword
    )
    userRepository.insert(user)

    return ResponseBodyUser(
      accountName = user.accountName
    )
  }
}