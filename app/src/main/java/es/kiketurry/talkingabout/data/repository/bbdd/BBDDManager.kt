package es.kiketurry.talkingabout.data.repository.bbdd

import android.content.Context
import androidx.room.Room

class BBDDManager private constructor(private val context: Context) {
    companion object {
        var INSTANCE: BBDDManager? = null

        @Synchronized
        fun getInstance(context: Context): BBDDManager {
            if (INSTANCE == null) {
                INSTANCE = BBDDManager(context)
            }
            return INSTANCE!!
        }
    }

    init {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "talking-about-bbdd").build()
    }
}