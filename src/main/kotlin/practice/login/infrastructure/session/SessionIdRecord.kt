package practice.login.infrastructure.session

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Table
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdEntity
import practice.login.domain.session.SessionIdExpireAt
import practice.login.domain.user.UserId

@Entity(immutable = true)
@Table(name = "session_ids")
data class SessionIdRecord(
  @Id
  @Column(name = "session_id")
  val sessionId: SessionId,
  @Column(name = "user_id")
  val userId: UserId,
  @Column(name = "expire_at")
  val expireAt: SessionIdExpireAt
) {

  companion object {

    fun from(
      sessionIdEntity: SessionIdEntity
    ) = SessionIdRecord(
      sessionId = sessionIdEntity.sessionId,
      userId = sessionIdEntity.userId,
      expireAt = sessionIdEntity.expireAt
    )
  }

  fun toEntity() = SessionIdEntity.of(
    sessionId = sessionId,
    userId = userId,
    expireAt = expireAt
  )
}