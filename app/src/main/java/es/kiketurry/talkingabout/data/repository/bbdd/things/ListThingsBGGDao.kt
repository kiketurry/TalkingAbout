package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.*

@Dao
interface ListThingsBGGDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Delete
    suspend fun delete(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Update
    suspend fun update(listThingsBGGRoomEntity: ListThingsBGGRoomEntity)

    @Query("SELECT * FROM listThingsBGGRoomEntity")
    fun getAll(): List<ListThingsBGGRoomEntity>

    @Query("SELECT * FROM listThingsBGGRoomEntity WHERE userBGG IN (:usersBGG)")
    fun loadAllListByUsersBGG(usersBGG: IntArray): List<ListThingsBGGRoomEntity>

    @Query("SELECT * FROM listThingsBGGRoomEntity WHERE userBGG LIKE :userBGG LIMIT 1")
    suspend fun findByUserBGG(userBGG: String): ListThingsBGGRoomEntity
}