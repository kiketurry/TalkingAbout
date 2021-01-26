package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.*

@Dao
interface ThingBGGDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg thingBGGRoomEntity: ThingBGGRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(thingBGGRoomEntity: ThingBGGRoomEntity)

    @Delete
    suspend fun delete(thingBGGRoomEntity: ThingBGGRoomEntity)

    @Update
    suspend fun update(thingBGGRoomEntity: ThingBGGRoomEntity)

    @Query("SELECT * FROM thingBGGRoomEntity")
    suspend fun getAllThings(): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE thingId IN (:thingIds)")
    suspend fun getAllThingsByIds(thingIds: IntArray): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE thingId LIKE :thingId LIMIT 1")
    suspend fun findByThingId(thingId: Int): ThingBGGRoomEntity

    @Query("SELECT EXISTS(SELECT * FROM thingBGGRoomEntity WHERE thingId = :thingId)")
    suspend fun isRowExist(thingId: Int): Boolean

    @Query("DELETE FROM thingBGGRoomEntity")
    suspend fun deleteAll()
}