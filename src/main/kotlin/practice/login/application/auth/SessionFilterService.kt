package practice.login.application.auth

import org.springframework.stereotype.Service
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class SessionFilterService(
  private val sessionService: SessionService
) {

  fun getSessionIdFromRequest(
    request: ServletRequest?
  ): SessionId? {

    val cookies: Array<Cookie> = (request as HttpServletRequest).cookies

    val sessionId: SessionId? =
      if (!cookies.isNullOrEmpty()) {
        cookies.firstOrNull {
          it.name == CookieConst.SESSION_ID_NAME
        }?.let { SessionId.of(it.value) }
      } else null

    return sessionId
  }

  fun upsertSessionId(
    response: ServletResponse?,
    sessionId: SessionId?
  ) {
    sessionId?.let { it ->
      sessionService.updateSessionId(
        sessionId = it
      )?.let { sessionIdEntity ->
        sessionService.setSessionIdToResponse(
          response = response as HttpServletResponse,
          sessionIdEntity = sessionIdEntity
        )
      }
    }
  }
}