package practice.login.domain.user

data class UserEntity private constructor(
  val id: UserId,
  val accountName: UserAccountName,
  val hashedPassword: UserHashedPassword,
) {

  companion object {

    fun new(
      accountName: UserAccountName,
      hashedPassword: UserHashedPassword
    ) = UserEntity(
      id = UserId.new(),
      accountName = accountName,
      hashedPassword = hashedPassword,
    )

    fun of(
      id: UserId,
      accountName: UserAccountName,
      hashedPassword: UserHashedPassword
    ) = UserEntity(
      id = id,
      accountName = accountName,
      hashedPassword = hashedPassword
    )
  }
}