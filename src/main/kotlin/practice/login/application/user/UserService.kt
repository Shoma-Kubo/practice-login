package practice.login.application.user

import org.springframework.stereotype.Service
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserRepository

@Service
class UserService(
  private val userRepository: UserRepository
) {

  fun get(
    accountName: String
  ): UserEntity =
    userRepository.findByAccountName(accountName)
}