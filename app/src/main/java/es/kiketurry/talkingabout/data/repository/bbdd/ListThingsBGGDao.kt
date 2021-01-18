package es.kiketurry.talkingabout.data.repository.bbdd

import androidx.room.*

@Dao
interface ListThingsBGGDao {
    @Insert
    fun insertAll(vararg listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Delete
    fun delete(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Update
    fun update(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Query("SELECT * FROM listThingsBGGRoomEntity")
    fun getAll(): List<ListThingsBGGRoomEntity>

    @Query("SELECT * FROM listThingsBGGRoomEntity WHERE userBGG IN (:usersBGG)")
    fun loadAllListByUsersBGG(usersBGG: IntArray): List<ListThingsBGGRoomEntity>

    @Query("SELECT * FROM listThingsBGGRoomEntity WHERE userBGG LIKE :userBGG LIMIT 1")
    fun findByUserBGG(userBGG: String): ListThingsBGGRoomEntity
}