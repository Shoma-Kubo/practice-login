package practice.login.application.auth

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.application.common.CookieService
import practice.login.config.CookieConst
import javax.servlet.http.HttpServletResponse

@Service
class SignOutUseCase(
  private val cookieService: CookieService
) {

  fun signOut(
    modelAndView: ModelAndView,
    response: HttpServletResponse
  ): ModelAndView {

    cookieService.removeCookie(
      response = response,
      cookieName = CookieConst.AUTH_TOKEN_NAME
    )
    cookieService.removeCookie(
      response = response,
      cookieName = CookieConst.REFRESH_TOKEN_NAME
    )

    return modelAndView.apply {
      viewName = "auth/signOutComplete"
    }
  }
}