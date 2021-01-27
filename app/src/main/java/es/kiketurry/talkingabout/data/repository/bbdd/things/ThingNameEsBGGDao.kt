package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ThingNameEsBGGDao {
    @Query("SELECT * FROM thingNameEsBGGRoomEntity")
    suspend fun getAllThingsNameEs(): List<ThingNameEsBGGRoomEntity>

    @Query("SELECT * FROM thingNameEsBGGRoomEntity WHERE thingId LIKE :thingId LIMIT 1")
    suspend fun findByThingId(thingId: Int): ThingNameEsBGGRoomEntity
}