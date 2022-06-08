package practice.login.presentation.filter

import org.springframework.stereotype.Component
import practice.login.application.jsonWebToken.JsonWebTokenFilterService
import practice.login.config.HttpAttributeConst
import practice.login.domain.jsonWebToken.AuthTokenEntity
import practice.login.domain.user.UserId
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component
class JsonWebTokenFilter(
  private val jsonWebTokenFilterService: JsonWebTokenFilterService
) : Filter {

  override fun doFilter(
    request: ServletRequest?,
    response: ServletResponse?,
    chain: FilterChain
  ) {

    val authTokenEntity: AuthTokenEntity? = jsonWebTokenFilterService.getAuthTokenFromRequest(request)
    val userId: UserId? =
      jsonWebTokenFilterService.getAuthTokenFromRequest(request)?.let { tokenEntity ->
        if (tokenEntity.token.isValid()) tokenEntity.token.getUserId()
        else null
      }
    request?.setAttribute(HttpAttributeConst.USER_ID_NAME, userId)

    chain.doFilter(request, response)
  }
}