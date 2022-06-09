package practice.login.domain.user

interface UserRepository {

  fun insert(user: UserEntity): UserEntity

  fun updateProfile(user: UserEntity): UserEntity

  fun findByAccountName(accountName: UserAccountName): UserEntity

  fun findAccountNameById(id: UserId): UserAccountName
}