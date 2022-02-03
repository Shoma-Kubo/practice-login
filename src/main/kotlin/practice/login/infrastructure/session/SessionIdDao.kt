package practice.login.infrastructure.session

import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.Result
import practice.login.domain.session.SessionId
import practice.login.domain.user.UserId

@Dao
@ConfigAutowireable
interface SessionIdDao {

  @Insert
  fun insert(sessionIdRecord: SessionIdRecord): Result<SessionIdRecord>

  @Delete(sqlFile = true)
  fun deleteByUserId(userId: UserId): Int

  @Select
  fun findBySessionId(sessionId: SessionId): SessionIdRecord?
}