package practice.login.infrastructure.user

import org.seasar.doma.Dao
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Update
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.Result

@Dao
@ConfigAutowireable
interface UserDao {

  @Insert
  fun insert(user: UserRecord): Result<UserRecord>

  @Update
  fun updateProfile(user: UserRecord): Result<UserRecord>

  @Select
  fun findByAccountName(accountName: String): UserRecord?
}