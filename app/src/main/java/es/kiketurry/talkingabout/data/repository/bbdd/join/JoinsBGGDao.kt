package es.kiketurry.talkingabout.data.repository.bbdd.join

import androidx.room.Dao
import androidx.room.Query
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity

@Dao
interface JoinsBGGDao {
    @Query("SELECT thingBGGRoomEntity.* FROM thingBGGRoomEntity INNER JOIN thingUserBGGRoomEntity ON (thingBGGRoomEntity.thingId = thingUserBGGRoomEntity.thingId AND thingUserBGGRoomEntity.userBGG = :userBGG)")
    suspend fun getAllThingsUser(userBGG: String): List<ThingBGGRoomEntity>
}