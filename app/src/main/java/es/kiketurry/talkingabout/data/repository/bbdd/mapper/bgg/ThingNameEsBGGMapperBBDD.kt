package es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.ThingNameEsBGGModel
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.BBDDMapperModel
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingNameEsBGGRoomEntity

class ThingNameEsBGGMapperBBDD : BBDDMapperModel<ThingNameEsBGGModel, ThingNameEsBGGRoomEntity> {
    override fun toBBDD(model: ThingNameEsBGGModel): ThingNameEsBGGRoomEntity {
        return ThingNameEsBGGRoomEntity(model.thingId, model.nameEs)
    }

    override fun toModel(bbdd: ThingNameEsBGGRoomEntity): ThingNameEsBGGModel {
        return ThingNameEsBGGModel(bbdd.thingId, bbdd.nameEs)
    }

    fun getMapTranslate(bbdd: List<ThingNameEsBGGRoomEntity>): MutableMap<Int, String> {
        val translateMap: MutableMap<Int, String> = mutableMapOf()

        bbdd.forEach { thingNameEsBGGRoomEntity ->
            translateMap.put(thingNameEsBGGRoomEntity.thingId, thingNameEsBGGRoomEntity.nameEs)
        }

        return translateMap
    }
}