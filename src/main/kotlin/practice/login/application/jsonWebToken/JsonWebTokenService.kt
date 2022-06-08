package practice.login.application.jsonWebToken

import org.springframework.stereotype.Service
import practice.login.application.common.CookieService
import practice.login.config.CookieConst
import practice.login.domain.jsonWebToken.AuthTokenEntity
import practice.login.domain.jsonWebToken.RefreshTokenEntity
import practice.login.utility.Utils.toAge
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class JsonWebTokenService(
  private val cookieService: CookieService
) {

  fun setAuthTokenToResponse(
    response: HttpServletResponse,
    authTokenEntity: AuthTokenEntity
  ) {

    val cookie = Cookie(CookieConst.AUTH_TOKEN_NAME, authTokenEntity.token.value)
    cookie.maxAge = authTokenEntity.token.getExpireAt()?.value?.toAge() ?: 0

    cookieService.addCookie(
      response,
      cookie
    )
  }

  fun setRefreshTokenToResponse(
    response: HttpServletResponse,
    refreshTokenEntity: RefreshTokenEntity
  ) {

    val cookie = Cookie(CookieConst.AUTH_TOKEN_NAME, refreshTokenEntity.token.value)
    cookie.maxAge = refreshTokenEntity.token.getExpireAt()?.value?.toAge() ?: 0

    cookieService.addCookie(
      response,
      cookie
    )
  }
}