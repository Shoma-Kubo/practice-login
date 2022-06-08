package practice.login.application.jsonWebToken

import org.springframework.stereotype.Service
import practice.login.config.CookieConst
import practice.login.domain.jsonWebToken.AuthTokenEntity
import practice.login.domain.jsonWebToken.JsonWebToken
import practice.login.domain.jsonWebToken.RefreshTokenEntity
import javax.servlet.ServletRequest
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest

@Service
class JsonWebTokenFilterService() {

  fun getAuthTokenFromRequest(
    request: ServletRequest?
  ): AuthTokenEntity? {

    val cookies: Array<Cookie>? = (request as HttpServletRequest).cookies

    val authTokenEntity: AuthTokenEntity? =
      if (!cookies.isNullOrEmpty()) {
        cookies.firstOrNull {
          it.name == CookieConst.AUTH_TOKEN_NAME
        }?.let {
          AuthTokenEntity.of(
            token = JsonWebToken.of(it.value)
          )
        }
      } else null

    return authTokenEntity
  }

  fun getRefreshTokenFromRequest(
    request: ServletRequest?
  ): RefreshTokenEntity? {

    val cookies: Array<Cookie> = (request as HttpServletRequest).cookies

    val refreshTokenEntity: RefreshTokenEntity? =
      if (!cookies.isNullOrEmpty()) {
        cookies.firstOrNull {
          it.name == CookieConst.REFRESH_TOKEN_NAME
        }?.let {
          RefreshTokenEntity.of(
            token = JsonWebToken.of(it.value)
          )
        }
      } else null

    return refreshTokenEntity
  }
}