package es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.BBDDMapperModel
import es.kiketurry.talkingabout.data.repository.bbdd.things.ListThingsBGGRoomEntity
import java.lang.reflect.Type

class ListUserBGGMapperBBDD : BBDDMapperModel<ListUserBGGModel, ListThingsBGGRoomEntity> {
    override fun toBBDD(model: ListUserBGGModel): ListThingsBGGRoomEntity {

        val listThingsToJson = Gson().toJson(model.listThings)

        return ListThingsBGGRoomEntity(
            model.userBGG,
            model.totalThings.toString(),
            model.totalBoardGames.toString(),
            model.totalExpansions.toString(),
            listThingsToJson,
            model.dateUpdate
        )
    }

    override fun toModel(bbdd: ListThingsBGGRoomEntity): ListUserBGGModel {

        val listThingsType: Type = object : TypeToken<ArrayList<String>>() {}.type
        val listThingsFromJson: ArrayList<String> = Gson().fromJson(bbdd.listThings, listThingsType)

        return ListUserBGGModel(
            bbdd.userBGG,
            bbdd.totalThings.toInt(),
            bbdd.totalBoardGames.toInt(),
            bbdd.totalExpansions.toInt(),
            listThingsFromJson,
            bbdd.dateUpdate
        )
    }

}