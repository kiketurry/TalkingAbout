package es.kiketurry.talkingabout.data.repository.remote.responses.breeds

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("adaptability") val adaptability: Int,
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("affection_level") val affectionLevel: Int,
    @SerializedName("child_friendly") val childFriendly: Int,
    @SerializedName("dog_friendly") val dogFriendly: Int,
)