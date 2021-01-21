package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.*

@Dao
interface ListThingsBGGDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

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