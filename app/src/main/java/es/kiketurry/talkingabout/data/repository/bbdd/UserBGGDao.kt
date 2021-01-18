package es.kiketurry.talkingabout.data.repository.bbdd

import androidx.room.*

@Dao
interface UserBGGDao {
    @Insert
    fun insertAll(vararg userBGGRoomEntity: UserBGGRoomEntity)

    @Delete
    fun delete(userBGGRoomEntity: UserBGGRoomEntity)

    @Update
    fun update(userBGGRoomEntity: UserBGGRoomEntity)

    @Query("SELECT * FROM userbggroomentity")
    fun getAllUsersBGG(): List<UserBGGRoomEntity>

    @Query("SELECT * FROM userbggroomentity WHERE userBGG IN (:userBGG)")
    fun loadAllUsersByUserBGG(userBGG: IntArray): List<UserBGGRoomEntity>

    @Query("SELECT * FROM userBGGRoomEntity WHERE userBGG LIKE :userBGG LIMIT 1")
    fun findByUserBGG(userBGG: String): UserBGGRoomEntity
}