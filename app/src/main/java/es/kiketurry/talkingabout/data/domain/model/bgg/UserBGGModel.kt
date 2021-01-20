package es.kiketurry.talkingabout.data.domain.model.bgg

import java.io.Serializable

data class UserBGGModel(
    var userBGG: String = "",
    var name: String = "",
    var email: String = "",
    var prefix: String = "",
    var phone: String = "",
) : Serializable