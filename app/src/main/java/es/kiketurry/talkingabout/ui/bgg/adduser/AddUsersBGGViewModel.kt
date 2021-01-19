package es.kiketurry.talkingabout.ui.bgg.adduser

import android.app.Application
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.runBlocking

class AddUsersBGGViewModel(application: Application, var appDatabase: AppDatabase) : BaseViewModel(application) {
    fun addUserBGG(userBGG: String, name: String, email: String, prefix: String, phone: String) {
        runBlocking {
            appDatabase.UserBGGDao().insert(UserBGGRoomEntity(userBGG, name, email, prefix, phone))
        }
    }

    override val TAG: String? get() = AddUsersBGGViewModel::class.qualifiedName

}