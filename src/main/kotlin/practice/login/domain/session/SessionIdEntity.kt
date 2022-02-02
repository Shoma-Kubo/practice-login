package practice.login.domain.session

import practice.login.utility.Utils.toBase64URLSafeString
import java.time.LocalDateTime
import java.util.*

data class SessionIdEntity private constructor(
  val sessionId: String,
  val accountName: String,
  val expireAt: LocalDateTime
) {

  companion object {

    fun new(
      accountName: String,
    ) = SessionIdEntity(
      sessionId = UUID.randomUUID().toBase64URLSafeString(),
      accountName = accountName,
      expireAt = LocalDateTime.now().plusMinutes(5)
    )
  }
}