package practice.login.utility

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

object Utils {

  fun String.toBase64URLSafeString(): String =
    Base64.getEncoder().encodeToString(this.toByteArray())

  fun LocalDateTime.toAge(): Long =
    ChronoUnit.SECONDS.between(LocalDateTime.now(), this)

  fun <T> nullOnNotFound(lambda: () -> T): T? =
    try {
      lambda()
      // TODO Exception -> NotFoundException
    } catch (_: Exception) {
      null
    }
}