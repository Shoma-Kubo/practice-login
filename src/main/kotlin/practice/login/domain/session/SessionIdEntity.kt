package practice.login.domain.session

import practice.login.domain.user.UserId

data class SessionIdEntity private constructor(
  val sessionId: SessionId,
  val userId: UserId,
  val expireAt: SessionIdExpireAt
) {

  companion object {

    fun new(
      userId: UserId,
    ) = SessionIdEntity(
      sessionId = SessionId.new(),
      userId = userId,
      expireAt = SessionIdExpireAt.new()
    )

    fun of(
      sessionId: SessionId,
      userId: UserId,
      expireAt: SessionIdExpireAt
    ) = SessionIdEntity(
      sessionId = sessionId,
      userId = userId,
      expireAt = expireAt
    )
  }
}