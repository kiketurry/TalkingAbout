package es.kiketurry.talkingabout.data.repository.remote.responses.breeds

import com.google.gson.annotations.SerializedName

data class BreedPhotoResponse(
    @SerializedName("url") var url: String
)