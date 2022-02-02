package practice.login.presentation.responseBody

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class ResponseBodyUser @JsonCreator constructor(

  @get:NotNull
  @JsonProperty("accountName") val accountName: kotlin.String
)