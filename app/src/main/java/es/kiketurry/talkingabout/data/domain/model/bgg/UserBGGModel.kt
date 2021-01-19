package es.kiketurry.talkingabout.data.domain.model.bgg

import java.io.Serializable

data class UserBGGModel(
    val userBGG: String = "",
    val name: String = "",
    val email: String = "",
    val prefix: String = "",
    val phone: String = "",
) : Serializable