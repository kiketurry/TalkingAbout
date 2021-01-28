package es.kiketurry.talkingabout.data.repository.bbdd.join

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity

@Dao
interface JoinsBGGDao {
    @Query("SELECT thingBGGRoomEntity.* FROM thingBGGRoomEntity INNER JOIN thingUserBGGRoomEntity ON (thingBGGRoomEntity.thingId = thingUserBGGRoomEntity.thingId AND thingUserBGGRoomEntity.userBGG = :userBGG)")
    suspend fun getAllThingsUser(userBGG: String): List<ThingBGGRoomEntity>

    @Query("SELECT thingBGGRoomEntity.* FROM thingBGGRoomEntity INNER JOIN thingUserBGGRoomEntity ON (thingBGGRoomEntity.thingId = thingUserBGGRoomEntity.thingId AND thingUserBGGRoomEntity.userBGG = :userBGG)")
    fun getAllThingsUserLiveData(userBGG: String): LiveData<List<ThingBGGRoomEntity>>

    @Query("SELECT thingBGGRoomEntity.* FROM thingBGGRoomEntity INNER JOIN thingUserBGGRoomEntity ON (thingBGGRoomEntity.thingId = thingUserBGGRoomEntity.thingId AND thingUserBGGRoomEntity.userBGG = :userBGG) WHERE thingBGGRoomEntity.type = 'TYPE_THING_BOARDGAME'")
    fun getAllThingsBoardGameUserLiveData(userBGG: String): LiveData<List<ThingBGGRoomEntity>>

    @Query("SELECT thingBGGRoomEntity.* FROM thingBGGRoomEntity INNER JOIN thingUserBGGRoomEntity ON (thingBGGRoomEntity.thingId = thingUserBGGRoomEntity.thingId AND thingUserBGGRoomEntity.userBGG = :userBGG) WHERE thingBGGRoomEntity.type = 'TYPE_THING_EXPANSION'")
    fun getAllThingsExpansionUserLiveData(userBGG: String): LiveData<List<ThingBGGRoomEntity>>
}