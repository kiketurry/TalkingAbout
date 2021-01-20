package es.kiketurry.talkingabout.data.domain.model.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_UNKNOW
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW
import java.io.Serializable

data class ThingBGGModel(
    var id: Int = -1,
    var type: TypeThingBGG = TYPE_THING_UNKNOW,
    var image: String = "",
    var nameFirst: String = "",
    var nameEs: String = "",
    var nameList: ArrayList<String> = ArrayList(),
    var description: String = "",
    var yearPublished: String = "",
    var playersNumber: String = "",
    var playersRecommendedCommunity: String = "",
    var ageMin: String = "",
    var ageMinRecommendedCommunity: String = "",
    var time: String = "",
    var weight: Double = 0.0,
    var languageDependence: LanguageDependenceThingBGG = LANGUAGE_DEPENDENCE_THING_UNKNOW,
    var rank: String = "",
    var dateUpdate: Long = 0L,
) : Serializable {
    enum class TypeThingBGG { TYPE_THING_UNKNOW, TYPE_THING_BOARDGAME, TYPE_THING_EXPANSION }
    enum class LanguageDependenceThingBGG { LANGUAGE_DEPENDENCE_THING_UNKNOW, LANGUAGE_DEPENDENCE_THING_NO_NECESARY, LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY, LANGUAGE_DEPENDENCE_THING_MODERATE, LANGUAGE_DEPENDENCE_THING_EXTENSIVE, LANGUAGE_DEPENDENCE_THING_UNPLAYABLE }
}