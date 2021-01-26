package es.kiketurry.talkingabout.data.repository.bbdd.thinguser

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ThingUserBGGDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg thingUserBGGRoomEntity: ThingUserBGGRoomEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(thingUserBGGRoomEntity: ThingUserBGGRoomEntity)

    @Delete
    suspend fun delete(thingUserBGGRoomEntity: ThingUserBGGRoomEntity)

    @Update
    suspend fun update(thingUserBGGRoomEntity: ThingUserBGGRoomEntity)

    @Query("SELECT * FROM thingUserBGGRoomEntity WHERE userBGG IN (:userBGG)")
    suspend fun getAllThingsUserByUserBGG(userBGG: String): List<ThingUserBGGRoomEntity>
}