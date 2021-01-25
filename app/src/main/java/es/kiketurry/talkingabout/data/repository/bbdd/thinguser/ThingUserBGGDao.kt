package es.kiketurry.talkingabout.data.repository.bbdd.thinguser

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Update

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
}