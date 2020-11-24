package es.kiketurry.talkingabout.data.repository.sharedpreferences

import android.content.Context
import es.kiketurry.talkingabout.injection.holder.SingletonHolderOneParameter

class SharedPreferencesManager private constructor(private val context: Context) {
    companion object {
        var INSTANCE : SharedPreferencesManager? = null

        @Synchronized fun getInstance(context: Context): SharedPreferencesManager{
            if (INSTANCE == null){
                INSTANCE = SharedPreferencesManager(context)
            }
            return INSTANCE!!
        }
    }
}