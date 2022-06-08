package practice.login.application.common

import org.springframework.stereotype.Service
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Service
class CookieService {

  fun addCookie(
    response: HttpServletResponse,
    cookie: Cookie
  ) = response.addCookie(cookie)

  fun removeCookie(
    response: HttpServletResponse,
    cookieName: String
  ) {
    val cookie = Cookie(cookieName, "")
    cookie.maxAge = 0
    response.addCookie(cookie)
  }
}