package practice.login.domain.jsonWebToken

import practice.login.config.CookieConst
import practice.login.domain.user.UserId

data class RefreshTokenEntity(
  val token: JsonWebToken
) {

  companion object {

    @JvmStatic
    fun of(
      token: JsonWebToken
    ) = RefreshTokenEntity(
      token = token
    )

    fun new(
      userId: UserId
    ) = RefreshTokenEntity(
      token = JsonWebToken.new(
        payload = mapOf(
          Pair(
            CookieConst.JSON_WEB_TOKEN_PAYLOAD_USER_ID,
            userId.value
          ),
          Pair(
            CookieConst.JSON_WEB_TOKEN_PAYLOAD_EXPIRE_AT,
            JsonWebTokenExpireAt.new(1).toFormattedString()
          )
        )
      )
    )
  }
}