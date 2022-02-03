package practice.login.domain.session

import practice.login.domain.user.UserId

interface SessionIdRepository {

  fun insert(sessionIdEntity: SessionIdEntity): SessionIdEntity

  fun deleteByUserId(userId: UserId): Int

  fun findBySessionId(sessionId: SessionId): SessionIdEntity
}