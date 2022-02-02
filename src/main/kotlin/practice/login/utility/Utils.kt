package practice.login.utility

import org.apache.commons.codec.binary.Base64
import java.nio.ByteBuffer
import java.util.*

object Utils {

  fun UUID.toBase64URLSafeString(): String {
    val bb: ByteBuffer = ByteBuffer.wrap(ByteArray(size = 16))
    bb.putLong(this.mostSignificantBits)
    bb.putLong(this.leastSignificantBits)
    return Base64.encodeBase64URLSafeString(bb.array())
  }

  fun <T> nullOnNotFound(lambda: () -> T): T? =
    try {
      lambda()
    } catch (_: Exception) {
      null
    }
}