package practice.login.utility

import org.apache.tomcat.util.codec.binary.Base64
import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

object Utils {

  fun UUID.toBase64URLSafeStringFromUUID(): String {
    val bb: ByteBuffer = ByteBuffer.wrap(ByteArray(size = 16))
    bb.putLong(this.mostSignificantBits)
    bb.putLong(this.leastSignificantBits)
    return Base64.encodeBase64URLSafeString(bb.array())
  }

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