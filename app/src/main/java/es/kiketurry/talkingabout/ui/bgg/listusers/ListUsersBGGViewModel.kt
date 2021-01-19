package es.kiketurry.talkingabout.ui.bgg.listusers

import android.app.Application
import androidx.lifecycle.LiveData
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.users.UserBGGRoomEntity
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class ListUsersBGGViewModel(application: Application, var appDatabase: AppDatabase) : BaseViewModel(application) {

    override val TAG: String? get() = ListUsersBGGViewModel::class.qualifiedName

    var usersListMutableLiveData: LiveData<List<UserBGGRoomEntity>> = appDatabase.UserBGGDao().getAllUsersBGG()

}