package practice.login.infrastructure.session

import org.springframework.stereotype.Repository
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdEntity
import practice.login.domain.session.SessionIdRepository
import practice.login.domain.user.UserId

@Repository
class SessionIdRepositoryImpl(
  private val sessionIdDao: SessionIdDao
) : SessionIdRepository {

  override fun insert(sessionIdEntity: SessionIdEntity): SessionIdEntity =
    sessionIdDao.insert(SessionIdRecord.from(sessionIdEntity)).entity.toEntity()

  override fun deleteByUserId(userId: UserId): Int =
    sessionIdDao.deleteByUserId(userId)

  override fun findBySessionId(sessionId: SessionId): SessionIdEntity =
    sessionIdDao.findBySessionId(sessionId)?.toEntity()
      ?: throw Exception("Session ID Not Found")
}