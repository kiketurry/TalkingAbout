package es.kiketurry.talkingabout.ui.bgg.listusers

import android.app.Application
import androidx.lifecycle.LiveData
import es.kiketurry.talkingabout.data.repository.bbdd.BBDDManager
import es.kiketurry.talkingabout.data.repository.bbdd.UserBGGRoomEntity
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class ListUsersBGGViewModel(application: Application, var bbddManager: BBDDManager) : BaseViewModel(application) {

    override val TAG: String? get() = ListUsersBGGViewModel::class.qualifiedName

    var usersListMutableLiveData: LiveData<List<UserBGGRoomEntity>> = bbddManager.db.UserBGGDao().getAllUsersBGG()

}