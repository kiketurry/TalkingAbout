package es.kiketurry.talkingabout.data.domain.model.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_UNKNOW
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW
import java.io.Serializable

data class ThingBGGModel(
    var thingId: Int = -1,
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
    var weight: String = "-",
    var languageDependence: LanguageDependenceThingBGG = LANGUAGE_DEPENDENCE_THING_UNKNOW,
    var rank: String = "",
    var dateUpdate: Long = 0L,
) : Serializable {
    enum class TypeThingBGG { TYPE_THING_UNKNOW, TYPE_THING_BOARDGAME, TYPE_THING_EXPANSION }
    enum class LanguageDependenceThingBGG { LANGUAGE_DEPENDENCE_THING_UNKNOW, LANGUAGE_DEPENDENCE_THING_NO_NECESARY, LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY, LANGUAGE_DEPENDENCE_THING_MODERATE, LANGUAGE_DEPENDENCE_THING_EXTENSIVE, LANGUAGE_DEPENDENCE_THING_UNPLAYABLE }

    fun bestName(): String {
        return if (nameEs.isBlank()) {
            nameFirst
        } else {
            nameEs
        }
    }

    fun getStringType(): String {
        return when (type) {
            TYPE_THING_UNKNOW -> "-"
            TypeThingBGG.TYPE_THING_BOARDGAME -> "Juego"
            TypeThingBGG.TYPE_THING_EXPANSION -> "Expansión"
        }
    }

    fun getStringLanguageDependence(): String {
        return when (languageDependence) {
            LANGUAGE_DEPENDENCE_THING_UNKNOW -> "-"
            LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_NO_NECESARY -> "Independiente del idioma"
            LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY -> "Algún texto necesario, pero poca cosa"
            LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_MODERATE -> "Texto moderado, con una hoja de ayuda se puede salvar"
            LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_EXTENSIVE -> "Mucho texto, se recomienda tradu-maquetación"
            LanguageDependenceThingBGG.LANGUAGE_DEPENDENCE_THING_UNPLAYABLE -> "No se puede jugar sin conocer el idioma"
        }
    }

    fun getStringListNames(): String {
        var stringBuilder = StringBuilder("Otros nombres: ")
        if (nameList.isEmpty()) {
            stringBuilder.append("-")
        } else {
            nameList.forEachIndexed { index, name ->
                stringBuilder.append(name)
                if (index != nameList.size - 1) {
                    stringBuilder.append(", ")
                }
            }
        }

        return stringBuilder.toString()
    }
}