package es.kiketurry.talkingabout.data.repository.bbdd.thinguser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thingUserBGGRoomEntity")
data class ThingUserBGGRoomEntity(
    @PrimaryKey @ColumnInfo(name = "userBGG") val userBGG: String,
    @PrimaryKey @ColumnInfo(name = "thingId") val name: String?,
)