package practice.login.application.top

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.application.jsonWebToken.JsonWebTokenFilterService
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId
import practice.login.domain.user.UserRepository
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.http.HttpServletRequest

@Service
class TopPageUseCase(
  private val userRepository: UserRepository,
  private val jsonWebTokenFilterService: JsonWebTokenFilterService
) {

  fun top(
    modelAndView: ModelAndView,
    request: HttpServletRequest,
    userId: UserId?
  ): ModelAndView {

    val userAccountName: UserAccountName? =
      userId?.let {
        nullOnNotFound {
          userRepository.findAccountNameByUserId(userId)
        }
      }
    val isSignedIn: Boolean = userAccountName != null

    val untilExpireSeconds: Long =
      jsonWebTokenFilterService.getAuthTokenFromRequest(request)?.token?.getExpireAt()?.toUntilExpireSeconds() ?: 0

    return modelAndView.apply {
      viewName = "top/top"
      addObject("is_signed_in", isSignedIn)
      addObject("account_name", userAccountName?.value)
      addObject("until_expire_seconds", untilExpireSeconds)
    }
  }
}