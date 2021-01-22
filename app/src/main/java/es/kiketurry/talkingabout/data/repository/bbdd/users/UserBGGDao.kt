package es.kiketurry.talkingabout.data.repository.bbdd.users

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UserBGGDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg userBGGRoomEntity: UserBGGRoomEntity)

    @Insert(onConflict = REPLACE)
    suspend fun insert(userBGGRoomEntity: UserBGGRoomEntity)

    @Delete
    suspend fun delete(userBGGRoomEntity: UserBGGRoomEntity)

    @Update
    suspend fun update(userBGGRoomEntity: UserBGGRoomEntity)

    @Query("SELECT * FROM userbggroomentity")
    suspend fun getAllUsersBGG(): List<UserBGGRoomEntity>

    @Query("SELECT * FROM userbggroomentity WHERE userBGG IN (:userBGG)")
    suspend fun loadAllUsersByUserBGG(userBGG: IntArray): List<UserBGGRoomEntity>

    @Query("SELECT * FROM userBGGRoomEntity WHERE userBGG LIKE :userBGG LIMIT 1")
    suspend fun findByUserBGG(userBGG: String): UserBGGRoomEntity


    //LiveDatas
    @Query("SELECT * FROM userbggroomentity")
    fun getAllUsersBGGLiveData(): LiveData<List<UserBGGRoomEntity>>
}