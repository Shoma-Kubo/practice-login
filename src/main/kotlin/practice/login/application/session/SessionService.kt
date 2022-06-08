package practice.login.application.session

import org.springframework.stereotype.Service
import practice.login.application.common.CookieService
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdEntity
import practice.login.domain.session.SessionIdRepository
import practice.login.domain.user.UserId
import practice.login.utility.Utils.nullOnNotFound
import practice.login.utility.Utils.toAge
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class SessionService(
  private val sessionIdRepository: SessionIdRepository,
  private val cookieService: CookieService
) {

  fun isSignedIn(
    sessionId: SessionId?
  ): UserId? =
    sessionId?.let {
      nullOnNotFound {
        sessionIdRepository.findBySessionId(sessionId)
      }?.let { sessionIdEntity ->
        if (!sessionIdEntity.expireAt.hasExpired()) sessionIdEntity.userId
        else null
      }
    }

  fun SessionId.isValid(): Boolean =
    nullOnNotFound {
      sessionIdRepository.findBySessionId(this)
    }?.let { sessionIdEntity ->
      !sessionIdEntity.expireAt.hasExpired()
    } ?: false

  fun createSessionId(
    userId: UserId
  ): SessionIdEntity =
    sessionIdRepository.insert(
      sessionIdEntity = SessionIdEntity.new(userId = userId)
    )

  fun updateSessionId(
    sessionId: SessionId,
  ): SessionIdEntity? = nullOnNotFound {
    sessionIdRepository.findBySessionId(sessionId)
  }?.let { sessionIdEntity ->
    sessionIdRepository.deleteByUserId(sessionIdEntity.userId)
    val newSessionIdEntity: SessionIdEntity =
      SessionIdEntity.new(userId = sessionIdEntity.userId)
    sessionIdRepository.insert(newSessionIdEntity)
  }

  fun setSessionIdToResponse(
    response: HttpServletResponse,
    sessionIdEntity: SessionIdEntity
  ): SessionIdEntity {

    val cookie = Cookie(CookieConst.SESSION_ID_NAME, sessionIdEntity.sessionId.value)
    cookie.maxAge = sessionIdEntity.expireAt.value.toAge()

    cookieService.addCookie(
      response,
      cookie
    )
    return sessionIdEntity
  }
}
