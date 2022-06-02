package practice.login.application.auth

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView
import practice.login.domain.session.SessionId
import practice.login.domain.session.SessionIdRepository
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.http.HttpServletResponse

@Service
class SignOutUseCase(
  private val sessionIdRepository: SessionIdRepository
) {

  fun signOut(
    modelAndView: ModelAndView,
    response: HttpServletResponse,
    sessionId: SessionId?
  ): ModelAndView {

    sessionId?.let {
      nullOnNotFound {
        sessionIdRepository.findBySessionId(sessionId)
      }?.let { sessionIdEntity ->
        sessionIdRepository.deleteByUserId(sessionIdEntity.userId)
      }
    }

    return modelAndView.apply {
      viewName = "auth/signOutComplete"
    }
  }
}