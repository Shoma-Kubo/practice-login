package practice.login.application.user

import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service
import practice.login.application.common.CookieService
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdEntity
import practice.login.domain.session.SessionIdRepository
import practice.login.domain.user.UserId
import practice.login.utility.Utils
import practice.login.utility.Utils.toAge
import javax.servlet.http.HttpServletResponse

@Service
class SessionService(
  private val sessionIdRepository: SessionIdRepository,
  private val cookieService: CookieService
) {

  fun SessionId.isValid(): Boolean =
    Utils.nullOnNotFound {
      sessionIdRepository.findBySessionId(this)
    }?.let { sessionIdEntity ->
      !sessionIdEntity.expireAt.hasExpired()
    } ?: false

  fun upsertSessionId(
    response: HttpServletResponse,
    sessionId: SessionId? = null,
    userId: UserId? = null
  ): SessionIdEntity? =
    sessionId?.let {
      Utils.nullOnNotFound {
        sessionIdRepository.findBySessionId(sessionId)
      }?.let { sessionIdEntity ->
        sessionIdRepository.deleteByUserId(sessionIdEntity.userId)
        setSessionId(
          response = response,
          userId = sessionIdEntity.userId
        )
      }
    } ?: userId?.let { it ->
      sessionIdRepository.deleteByUserId(it)
      setSessionId(
        response = response,
        userId = it
      )
    } ?: throw Exception("Failed To Update")

  fun setSessionId(
    response: HttpServletResponse,
    userId: UserId
  ): SessionIdEntity {

    val sessionIdEntity = SessionIdEntity.new(userId = userId)
    sessionIdRepository.insert(sessionIdEntity = sessionIdEntity)

    val cookie: ResponseCookie.ResponseCookieBuilder =
      ResponseCookie
        .from(CookieConst.SESSION_ID_NAME, sessionIdEntity.sessionId.value)
        .maxAge(sessionIdEntity.expireAt.value.toAge())

    cookieService.addCookie(
      response,
      cookie
    )

    return sessionIdEntity
  }
}
