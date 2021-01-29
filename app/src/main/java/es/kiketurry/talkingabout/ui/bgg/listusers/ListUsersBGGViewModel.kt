package es.kiketurry.talkingabout.ui.bgg.listusers

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import es.kiketurry.talkingabout.data.domain.model.bgg.UserBGGModel
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.UserBGGMapperBBDD
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class ListUsersBGGViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val appDatabase: AppDatabase) :
    BaseViewModel(ioDispatcher, application) {

    override val TAG: String? get() = ListUsersBGGViewModel::class.qualifiedName

    var usersListMutableLiveData: MutableLiveData<ArrayList<UserBGGModel>> = MutableLiveData()

    private val userBGGMapper = UserBGGMapperBBDD()

    fun observeUsersBGGBBDD(lifecycleOwner: LifecycleOwner) {
        appDatabase.UserBGGDao().getAllUsersBGGLiveData().observe(lifecycleOwner, Observer { listUser ->
            usersListMutableLiveData.postValue(userBGGMapper.toModel(listUser))
        })
    }

    fun deleteUserBGG(userBGGModel: UserBGGModel) {
        runBlocking {
            appDatabase.UserBGGDao().delete(userBGGMapper.toBBDD(userBGGModel))
            appDatabase.ListThingsBGGDao().deleteByUserBGG(userBGGModel.userBGG)
        }
    }

    fun deleteAllThings() {
        runBlocking {
            appDatabase.ThingBGGDao().deleteAll()
        }
    }

}