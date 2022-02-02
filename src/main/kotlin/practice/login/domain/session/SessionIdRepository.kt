package practice.login.domain.session

interface SessionIdRepository {

  fun insert(sessionIdEntity: SessionIdEntity): SessionIdEntity

  fun updateProfile(sessionIdEntity: SessionIdEntity): Int

  fun findBySessionId(sessionId: String): SessionIdEntity
}