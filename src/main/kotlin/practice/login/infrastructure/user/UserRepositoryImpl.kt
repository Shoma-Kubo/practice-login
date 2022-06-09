package practice.login.infrastructure.user

import org.springframework.stereotype.Repository
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserId
import practice.login.domain.user.UserRepository

@Repository
class UserRepositoryImpl(
  private val userDao: UserDao
) : UserRepository {

  override fun insert(user: UserEntity): UserEntity =
    userDao.insert(UserRecord.from(user)).entity.toEntity()

  override fun updateProfile(user: UserEntity): UserEntity =
    userDao.updateProfile(UserRecord.from(user)).entity.toEntity()

  override fun findByAccountName(accountName: UserAccountName): UserEntity =
    userDao.findByAccountName(accountName)?.toEntity() ?: throw Exception("User Not Found")

  override fun findAccountNameById(id: UserId): UserAccountName =
    userDao.findAccountNameById(id) ?: throw Exception("User Not Found")
}