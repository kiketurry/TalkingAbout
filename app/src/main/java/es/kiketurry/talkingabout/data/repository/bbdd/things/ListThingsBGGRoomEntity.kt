package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//, foreignKeys = @ForeignKey(entity = UserBGGRoomEntity::class, parentColumns = "userBGG", childColumns = "userBGG", onDelete = CASCADE)
@Entity(tableName = "listThingsBGGRoomEntity")
data class ListThingsBGGRoomEntity(
    @PrimaryKey @ColumnInfo(name = "userBGG") val userBGG: String,
    @ColumnInfo(name = "totalThings") var totalThings: String,
    @ColumnInfo(name = "totalBoardGames") var totalBoardGames: String,
    @ColumnInfo(name = "totalExpansions") var totalExpansions: String,
    @ColumnInfo(name = "listThings") val listThings: String,
    @ColumnInfo(name = "dateUpdate") val dateUpdate: Long,
)