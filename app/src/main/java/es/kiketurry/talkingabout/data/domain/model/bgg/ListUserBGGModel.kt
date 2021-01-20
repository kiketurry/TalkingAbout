package es.kiketurry.talkingabout.data.domain.model.bgg

import java.io.Serializable

data class ListUserBGGModel(
    var userBGG: String = "",
    var totalThings: Int = 0,
    var totalBoardGames: Int = 0,
    var totalExpansions: Int = 0,
    var listThings: ArrayList<String> = ArrayList(),
    var dateUpdate: Long = 0L,
) : Serializable