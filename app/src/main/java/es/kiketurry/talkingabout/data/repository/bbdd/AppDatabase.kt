package es.kiketurry.talkingabout.data.repository.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserBGGRoomEntity::class,
        ListThingsBGGRoomEntity::class,
        ThingBGGRoomEntity::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserBGGDao(): UserBGGDao
    abstract fun ListThingsBGGDao(): ListThingsBGGDao
    abstract fun ThingBGGDao(): ThingBGGDao
}