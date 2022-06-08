package practice.login.infrastructure.user

import org.seasar.doma.Dao
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Update
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.Result
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId

@Dao
@ConfigAutowireable
interface UserDao {

  @Insert
  fun insert(user: UserRecord): Result<UserRecord>

  @Update
  fun updateProfile(user: UserRecord): Result<UserRecord>

  @Select
  fun findByAccountName(accountName: UserAccountName): UserRecord?

  @Select
  fun findAccountNameByUserId(userId: UserId): UserAccountName?

  @Select
  fun existByUserId(userId: UserId): Boolean
}