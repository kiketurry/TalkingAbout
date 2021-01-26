package es.kiketurry.talkingabout.data.repository.bbdd.thinguser

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity

@Entity(
    tableName = "thingUserBGGRoomEntity",
    primaryKeys = ["userBGG", "thingId"],
    foreignKeys = [ForeignKey(
        entity = UserBGGRoomEntity::class,
        parentColumns = ["userBGG"],
        childColumns = ["userBGG"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ThingUserBGGRoomEntity(
    @ColumnInfo(name = "userBGG") val userBGG: String,
    @ColumnInfo(name = "thingId") val thingId: Int,
)