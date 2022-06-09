package practice.login.presentation.responseBody

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class ResponseBodyIsSignedIn @JsonCreator constructor(

  @get:NotNull
  @JsonProperty("isSignedIn") val isSignedIn: kotlin.Boolean
)