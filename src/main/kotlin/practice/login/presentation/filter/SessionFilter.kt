package practice.login.presentation.filter

import org.springframework.stereotype.Component
import practice.login.application.user.SessionService
import practice.login.config.CookieConst
import practice.login.domain.session.SessionId
import practice.login.domain.user.UserId
import practice.login.domain.user.UserRepository
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class SessionFilter(
  private val sessionService: SessionService,
  private val userRepository: UserRepository
) : Filter {

  override fun doFilter(
    request: ServletRequest?,
    response: ServletResponse?,
    chain: FilterChain
  ) {

    chain.doFilter(request, response)

    val httpRequest = request as HttpServletRequest
    val cookies = httpRequest.cookies

    val sessionId =
      if (!cookies.isNullOrEmpty()) {
        httpRequest.cookies.firstOrNull {
          it.name == CookieConst.SESSION_ID_NAME
        }?.let { SessionId.of(it.value) }
      } else null

    val userId: UserId? = sessionId?.let {
      nullOnNotFound {
        sessionService.upsertSessionId(
          response = response as HttpServletResponse,
          sessionId = it
        )
      }?.userId
    }
  }
}