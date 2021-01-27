package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thingNameEsBGGRoomEntity")
data class ThingNameEsBGGRoomEntity(
    @PrimaryKey @ColumnInfo(name = "thingId") val thingId: Int,
    @ColumnInfo(name = "nameEs") val nameEs: String,
)