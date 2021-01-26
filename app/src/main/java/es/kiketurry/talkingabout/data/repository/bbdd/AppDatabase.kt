package es.kiketurry.talkingabout.data.repository.bbdd

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.join.JoinsBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.things.ListThingsBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.things.ListThingsBGGRoomEntity
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity
import es.kiketurry.talkingabout.data.repository.bbdd.thinguser.ThingUserBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.thinguser.ThingUserBGGRoomEntity
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGDao
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity

@Database(
    entities = [
        UserBGGRoomEntity::class,
        ListThingsBGGRoomEntity::class,
        ThingBGGRoomEntity::class,
        ThingUserBGGRoomEntity::class,
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val TAG: String? = AppDatabase::class.simpleName

        var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, AppDatabase::class.java, "talking-about.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Log.d(TAG, "l> BBDD onCreate")
                                insertUsers(db)
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                Log.d(TAG, "l> BBDD onOpen")
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                                Log.d(TAG, "l> BBDD onDestructiveMigration")
                            }
                        })
                        .build()
            }
            return INSTANCE!!
        }

        private fun insertUsers(db: SupportSQLiteDatabase) {
            db.execSQL("INSERT INTO userBGGRoomEntity (userBGG, name, email, prefix, phone) VALUES('kiketurrydeveloper', 'Uno', 'kiketurry.developer@gmail.com', '34', '666555888')")
            db.execSQL("INSERT INTO userBGGRoomEntity (userBGG, name, email, prefix, phone) VALUES('kiketurrydeveloper2', 'Dos', 'kiketurry.developer@gmail.com', '34', '666555888')")
            db.execSQL("INSERT INTO userBGGRoomEntity (userBGG, name, email, prefix, phone) VALUES('kiketurrp', 'Enrique', 'kiketurry@gmail.com', '34', '666555888')")
        }
    }

    abstract fun UserBGGDao(): UserBGGDao
    abstract fun ListThingsBGGDao(): ListThingsBGGDao
    abstract fun ThingBGGDao(): ThingBGGDao
    abstract fun ThingUserBGGDao(): ThingUserBGGDao
    abstract fun JoinsBGGDao(): JoinsBGGDao
}
