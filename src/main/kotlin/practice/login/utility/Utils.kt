package practice.login.utility

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

object Utils {

  fun String.toBase64URLSafeString(): String =
    Base64.getEncoder().encodeToString(this.toByteArray())

  fun String.decodeFromBase64URLSafeString(): String =
    Base64.getDecoder().decode(this.toByteArray()).toString(Charsets.UTF_8)

  fun LocalDateTime.toAge(): Int =
    ChronoUnit.SECONDS.between(LocalDateTime.now(), this).toInt()

  fun <T> nullOnNotFound(lambda: () -> T): T? =
    try {
      lambda()
      // TODO Exception -> NotFoundException
    } catch (_: Exception) {
      null
    }

  fun Map<String, String>.toJsonString(): String =
    "{" + this.entries.joinToString(",") { "\"${it.key}\":\"${it.value}\"" } + "}"
}