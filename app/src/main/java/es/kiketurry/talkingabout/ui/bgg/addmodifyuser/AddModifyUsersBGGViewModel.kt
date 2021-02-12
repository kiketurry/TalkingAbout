package es.kiketurry.talkingabout.ui.bgg.addmodifyuser

import android.app.Application
import androidx.lifecycle.viewModelScope
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddModifyUsersBGGViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val appDatabase: AppDatabase) :
    BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = AddModifyUsersBGGViewModel::class.qualifiedName

    var modify: Boolean = false

    fun manageUserBGG(userBGG: String, name: String, email: String, prefix: String, phone: String) {
        if (modify) {
            updateUserBGG(userBGG, name, email, prefix, phone)
        } else {
            addUserBGG(userBGG, name, email, prefix, phone)
        }
    }

    private fun addUserBGG(userBGG: String, name: String, email: String, prefix: String, phone: String) {
        viewModelScope.launch {
            appDatabase.UserBGGDao().insert(UserBGGRoomEntity(userBGG, name, email, prefix, phone))
        }
    }

    private fun updateUserBGG(userBGG: String, name: String, email: String, prefix: String, phone: String) {
        viewModelScope.launch {
            appDatabase.UserBGGDao().update(UserBGGRoomEntity(userBGG, name, email, prefix, phone))
        }
    }
}