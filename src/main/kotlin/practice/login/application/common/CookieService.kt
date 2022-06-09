package practice.login.application.common

import com.google.common.net.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse

@Service
class CookieService {

  fun addCookie(
    response: HttpServletResponse,
    cookieBuilder: ResponseCookie.ResponseCookieBuilder
  ) = response.addHeader(
    HttpHeaders.SET_COOKIE,
    cookieBuilder.build().toString()
  )
}