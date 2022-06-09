package practice.login.application.top

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.application.auth.SessionFilterService
import practice.login.application.auth.SessionService
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdRepository
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId
import practice.login.domain.user.UserRepository
import javax.servlet.http.HttpServletRequest

@Service
class TopPageUseCase(
  private val sessionService: SessionService,
  private val sessionFilterService: SessionFilterService,
  private val sessionIdRepository: SessionIdRepository,
  private val userRepository: UserRepository
) {

  fun top(
    modelAndView: ModelAndView,
    request: HttpServletRequest
  ): ModelAndView {

    val sessionId: SessionId? = sessionFilterService.getSessionIdFromRequest(request)
    val userId: UserId? = sessionService.isSignedIn(sessionId)

    val isSignedIn: Boolean = userId != null
    val userAccountName: UserAccountName? = userId?.let { userRepository.findAccountNameById(it) }
    val untilExpireSeconds: Long =
      sessionId?.let { sessionIdRepository.findBySessionId(sessionId).expireAt.toUntilExpireSeconds() } ?: 0

    return modelAndView.apply {
      viewName = "top/top"
      addObject("is_signed_in", isSignedIn)
      addObject("account_name", userAccountName)
      addObject("until_expire_seconds", untilExpireSeconds)
    }
  }
}