package es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.*
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.*
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.BBDDMapperModel
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity
import java.lang.reflect.Type

class ThingBGGMapperBBDD : BBDDMapperModel<ThingBGGModel, ThingBGGRoomEntity> {

    override fun toBBDD(model: ThingBGGModel): ThingBGGRoomEntity {

        val nameListToJson = Gson().toJson(model.nameList)

        return ThingBGGRoomEntity(
            model.id,
            model.type.name,
            model.image,
            model.nameFirst,
            model.nameEs,
            nameListToJson,
            model.description,
            model.yearPublished,
            model.playersNumber,
            model.playersRecommendedCommunity,
            model.ageMin,
            model.ageMinRecommendedCommunity,
            model.time,
            model.weight.toString(),
            model.languageDependence.name,
            model.rank,
            model.dateUpdate
        )
    }

    override fun toModel(bbdd: ThingBGGRoomEntity): ThingBGGModel {
        val type: ThingBGGModel.TypeThingBGG = when (bbdd.type) {
            TYPE_THING_BOARDGAME.name -> {
                TYPE_THING_BOARDGAME
            }
            TYPE_THING_EXPANSION.name -> {
                TYPE_THING_EXPANSION
            }
            else -> {
                TYPE_THING_UNKNOW
            }
        }

        val languageDependence: ThingBGGModel.LanguageDependenceThingBGG = when (bbdd.languageDependence) {
            LANGUAGE_DEPENDENCE_THING_NO_NECESARY.name -> {
                LANGUAGE_DEPENDENCE_THING_NO_NECESARY
            }
            LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY.name -> {
                LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY
            }
            LANGUAGE_DEPENDENCE_THING_MODERATE.name -> {
                LANGUAGE_DEPENDENCE_THING_MODERATE
            }
            LANGUAGE_DEPENDENCE_THING_EXTENSIVE.name -> {
                LANGUAGE_DEPENDENCE_THING_EXTENSIVE
            }
            LANGUAGE_DEPENDENCE_THING_UNPLAYABLE.name -> {
                LANGUAGE_DEPENDENCE_THING_UNPLAYABLE
            }
            else -> {
                LANGUAGE_DEPENDENCE_THING_UNKNOW
            }
        }

        val nameListType: Type = object : TypeToken<ArrayList<String>>() {}.type
        val nameListFromJson: ArrayList<String> = Gson().fromJson(bbdd.nameList, nameListType)

        return ThingBGGModel(
            bbdd.id,
            type,
            bbdd.image,
            bbdd.nameFirst,
            bbdd.nameEs,
            nameListFromJson,
            bbdd.description,
            bbdd.yearPublished,
            bbdd.playersNumber,
            bbdd.playersRecommendedCommunity,
            bbdd.ageMin,
            bbdd.ageMinRecommendedCommunity,
            bbdd.time,
            bbdd.weight.toDouble(),
            languageDependence,
            bbdd.rank,
            bbdd.dateUpdate
        )
    }
}