package practice.login.presentation.filter

import org.springframework.stereotype.Component
import practice.login.application.auth.SessionFilterService
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
class SessionFilter(
  private val sessionFilterService: SessionFilterService
) : Filter {

  override fun doFilter(
    request: ServletRequest?,
    response: ServletResponse?,
    chain: FilterChain
  ) {

    val sessionId = sessionFilterService.getSessionIdFromRequest(request)
    
    chain.doFilter(request, response)

    /*
    sessionFilterService.upsertSessionId(
      response = response,
      sessionId = sessionId
    )
     */
  }
}