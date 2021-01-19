package es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.BBDDMapperModel
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity

class UserBGGMapper : BBDDMapperModel<UserBGGModel, UserBGGRoomEntity> {
    override fun toBBDD(model: UserBGGModel): UserBGGRoomEntity {
        return UserBGGRoomEntity(model.userBGG, model.name, model.email, model.prefix, model.phone)
    }

    override fun toModel(bbdd: UserBGGRoomEntity): UserBGGModel {
        return UserBGGModel(bbdd.userBGG, bbdd.name ?: "", bbdd.email ?: "", bbdd.prefix ?: "", bbdd.phone ?: "")
    }

    fun toModel(bbdd: List<UserBGGRoomEntity>): ArrayList<UserBGGModel> {
        val listUser = ArrayList<UserBGGModel>()

        bbdd.forEachIndexed { _, userBGGRoomEntity ->
            listUser.add(toModel(userBGGRoomEntity))
        }

        return listUser
    }

}