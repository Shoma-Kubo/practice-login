package practice.login.presentation.requestBody

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class RequestBodySignUp @JsonCreator constructor(

  @get:NotNull
  @JsonProperty("accountName") val accountName: kotlin.String,

  @get:NotNull
  @JsonProperty("password") val password: kotlin.String,
)