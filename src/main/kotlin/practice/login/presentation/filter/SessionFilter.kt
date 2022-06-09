package practice.login.presentation.filter

import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import practice.login.application.auth.SessionFilterService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionFilter(
  private val sessionFilterService: SessionFilterService
) : OncePerRequestFilter() {

  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

    val sessionId = sessionFilterService.getSessionIdFromRequest(request)

    filterChain.doFilter(request, response)

    sessionFilterService.upsertSessionId(
      response = response,
      sessionId = sessionId
    )
  }
}