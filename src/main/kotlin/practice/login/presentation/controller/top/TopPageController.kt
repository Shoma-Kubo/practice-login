package practice.login.presentation.controller.top

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import practice.login.application.top.TopPageUseCase
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/")
class TopPageController(
  private val topPageUseCase: TopPageUseCase
) {

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun top(
    modelAndView: ModelAndView,
    request: HttpServletRequest
  ): ModelAndView = topPageUseCase.top(
    modelAndView = modelAndView,
    request = request
  )
}