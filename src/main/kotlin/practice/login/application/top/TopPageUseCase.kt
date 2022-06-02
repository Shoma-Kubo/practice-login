package practice.login.application.top

import org.springframework.stereotype.Service
import org.springframework.web.servlet.ModelAndView

@Service
class TopPageUseCase {

  fun top(
    modelAndView: ModelAndView
  ): ModelAndView = modelAndView.apply {
    viewName = "top/top"
  }
}