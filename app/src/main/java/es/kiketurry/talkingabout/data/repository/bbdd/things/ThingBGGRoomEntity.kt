package es.kiketurry.talkingabout.data.repository.bbdd.things

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thingBGGRoomEntity")
data class ThingBGGRoomEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "type") val type: String = "",
    @ColumnInfo(name = "image") val image: String = "",
    @ColumnInfo(name = "nameFirst") val nameFirst: String = "",
    @ColumnInfo(name = "nameEs") val nameEs: String = "",
    @ColumnInfo(name = "nameList") val nameList: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "yearPublished") val yearPublished: String = "",
    @ColumnInfo(name = "playersNumber") val playersNumber: String = "",
    @ColumnInfo(name = "playersRecommendedCommunity") val playersRecommendedCommunity: String = "",
    @ColumnInfo(name = "ageMin") val ageMin: String = "",
    @ColumnInfo(name = "ageMinRecommendedCommunity") val ageMinRecommendedCommunity: String = "",
    @ColumnInfo(name = "time") val time: String = "",
    @ColumnInfo(name = "weight") val weight: String = "",
    @ColumnInfo(name = "languageDependence") val languageDependence: String = "",
    @ColumnInfo(name = "rank") val rank: String = "",
    @ColumnInfo(name = "dateUpdate") val dateUpdate: Long = 0L,
)