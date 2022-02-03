package practice.login.infrastructure.user

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Table
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserEntity
import practice.login.domain.user.UserId
import practice.login.domain.user.UserPassword

@Entity(immutable = true)
@Table(name = "users")
data class UserRecord(
  @Id
  @Column(name = "id")
  val id: UserId,
  @Column(name = "account_name")
  val accountName: UserAccountName,
  @Column(name = "password")
  val password: UserPassword
) {

  companion object {

    fun from(
      user: UserEntity
    ) = UserRecord(
      id = user.id,
      accountName = user.accountName,
      password = user.password
    )
  }

  fun toEntity() = UserEntity.of(
    id = id,
    accountName = accountName,
    password = password
  )
}