package practice.login.domain.user

interface UserRepository {

  fun insert(user: UserEntity): UserEntity

  fun updateProfile(user: UserEntity): UserEntity

  fun findByAccountName(accountName: UserAccountName): UserEntity

  fun findAccountNameByUserId(userId: UserId): UserAccountName

  fun existByUserId(userId: UserId): Boolean
}