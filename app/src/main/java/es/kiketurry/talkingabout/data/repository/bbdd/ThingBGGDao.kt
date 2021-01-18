package es.kiketurry.talkingabout.data.repository.bbdd

import androidx.room.*

@Dao
interface ThingBGGDao {
    @Insert
    fun insertAll(vararg thingBGGRoomEntity: ThingBGGRoomEntity)

    @Delete
    fun delete(thingBGGRoomEntity: ThingBGGRoomEntity)

    @Update
    fun update(thingBGGRoomEntity: ThingBGGRoomEntity)

    @Query("SELECT * FROM thingBGGRoomEntity")
    fun getAllThings(): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE id IN (:ids)")
    fun loadAllThingsByIds(ids: IntArray): List<ThingBGGRoomEntity>

    @Query("SELECT * FROM thingBGGRoomEntity WHERE id LIKE :id LIMIT 1")
    fun findByThingId(id: String): ThingBGGRoomEntity
}