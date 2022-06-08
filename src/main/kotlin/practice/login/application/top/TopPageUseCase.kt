package practice.login.application.top

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId
import practice.login.domain.user.UserRepository
import practice.login.utility.Utils.nullOnNotFound

@Service
class TopPageUseCase(
  private val userRepository: UserRepository
) {

  fun top(
    modelAndView: ModelAndView,
    userId: UserId?
  ): ModelAndView {

    val userAccountName: UserAccountName? =
      userId?.let {
        nullOnNotFound {
          userRepository.findAccountNameByUserId(userId)
        }
      }
    val isSignedIn: Boolean = userAccountName != null

    return modelAndView.apply {
      viewName = "top/top"
      addObject("is_signed_in", isSignedIn)
      addObject("account_name", userAccountName?.value)
    }
  }
}