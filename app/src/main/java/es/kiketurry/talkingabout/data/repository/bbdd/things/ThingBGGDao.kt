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
    fun getAllThings(): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE id IN (:ids)")
    fun loadAllThingsByIds(ids: IntArray): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE id LIKE :id LIMIT 1")
    suspend fun findByThingId(id: Int): ThingBGGRoomEntity

    @Query("SELECT EXISTS(SELECT * FROM thingBGGRoomEntity WHERE id = :id)")
    suspend fun isRowExist(id: Int): Boolean
}