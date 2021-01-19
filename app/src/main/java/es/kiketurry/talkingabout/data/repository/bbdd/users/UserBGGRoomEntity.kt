package es.kiketurry.talkingabout.data.repository.bbdd.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userBGGRoomEntity")
data class UserBGGRoomEntity(
    @PrimaryKey @ColumnInfo(name = "userBGG") val userBGG: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "prefix") val prefix: String?,
    @ColumnInfo(name = "phone") val phone: String?,
)