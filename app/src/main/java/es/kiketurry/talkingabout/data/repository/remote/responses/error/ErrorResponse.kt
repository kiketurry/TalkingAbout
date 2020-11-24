package es.kiketurry.talkingabout.data.repository.remote.responses.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") var message: String
)