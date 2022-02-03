package practice.login.domain.user

data class UserEntity private constructor(
  val id: UserId,
  val accountName: UserAccountName,
  val password: UserPassword,
) {

  companion object {

    fun new(
      accountName: UserAccountName,
      password: UserPassword
    ) = UserEntity(
      id = UserId.new(),
      accountName = accountName,
      password = password,
    )

    fun of(
      id: UserId,
      accountName: UserAccountName,
      password: UserPassword
    ) = UserEntity(
      id = id,
      accountName = accountName,
      password = password
    )
  }
}