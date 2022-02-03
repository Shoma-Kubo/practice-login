package practice.login.application.user

import org.springframework.stereotype.Service
import practice.login.domain.session.SessionId
import practice.login.domain.user.UserAccountName
import practice.login.domain.user.UserId
import practice.login.domain.user.UserPassword
import practice.login.domain.user.UserRepository
import practice.login.presentation.requestBody.RequestBodySignIn
import practice.login.presentation.responseBody.ResponseBodyIsSignedIn
import practice.login.utility.Utils.nullOnNotFound
import javax.servlet.http.HttpServletResponse

@Service
class SignInUseCase(
  private val userRepository: UserRepository,
  private val sessionService: SessionService
) {

  fun isSignedIn(
    userId: UserId?
  ): ResponseBodyIsSignedIn {

    return ResponseBodyIsSignedIn(
      isSignedIn = userId != null
    )
  }

  fun signIn(
    response: HttpServletResponse,
    sessionId: SessionId?,
    requestBodySignIn: RequestBodySignIn
  ) {

    val accountName = UserAccountName.of(requestBodySignIn.accountName)
    // TODO Encode password
    val encodedPassword =
      UserPassword.of(requestBodySignIn.password) // passwordEncoder.encode(requestBodySignIn.password)

    val userId: UserId = nullOnNotFound {
      userRepository.findUserIdByAccountNameAndPassword(
        accountName = accountName,
        password = encodedPassword
      )
    } ?: throw Exception("Sign In Failed")

    sessionService.upsertSessionId(
      response = response,
      userId = userId
    )
  }
}