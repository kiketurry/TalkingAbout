package es.kiketurry.talkingabout.data.repository.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.things.ListThingsBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.things.ListThingsBGGRoomEntity
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity

@Database(
    entities = [
        UserBGGRoomEntity::class,
        ListThingsBGGRoomEntity::class,
        ThingBGGRoomEntity::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "talking-about.db").build()
            }
            return INSTANCE!!
        }
    }

    abstract fun UserBGGDao(): UserBGGDao
    abstract fun ListThingsBGGDao(): ListThingsBGGDao
    abstract fun ThingBGGDao(): ThingBGGDao
}