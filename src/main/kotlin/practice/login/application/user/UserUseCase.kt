package practice.login.application.user

import org.springframework.stereotype.Service
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserRepository

@Service
class UserUseCase(
  private val userRepository: UserRepository
) {

  fun get(
    accountName: UserAccountName
  ): UserEntity =
    userRepository.findByAccountName(accountName)
}