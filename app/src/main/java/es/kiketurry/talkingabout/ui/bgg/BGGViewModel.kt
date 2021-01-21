package es.kiketurry.talkingabout.ui.bgg

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ListUserBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.things.ThingBGGRoomEntity
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class BGGViewModel(application: Application, var appDatabase: AppDatabase, val dataProvider: DataProvider) : BaseViewModel(application) {
    override val TAG: String? get() = BGGViewModel::class.qualifiedName

    companion object {
        const val ONE_MONTH_IN_MILLISECOND = 2629746000L
    }

    var userBGGSelectedMutableLiveData: MutableLiveData<String> = MutableLiveData()

    var getListUserBGGModel: ListUserBGGModel = ListUserBGGModel()
    var getListThings: ArrayList<ThingBGGModel> = ArrayList()

    var timeStampNow = Date().time

    fun setUserBGGSelected(userBGG: String) {
        userBGGSelectedMutableLiveData.postValue(userBGG)
        getListAndBoardGamesUser(userBGG)
    }

    private fun getListAndBoardGamesUser(userSelectedBGG: String) {
        loadingMutableLiveData.postValue(true)
        getBoardGamesByUser(userSelectedBGG)
    }

    private fun getBoardGamesByUser(userBGG: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getBoardGamesByUser(object : DataSourceCallbacks.GetBoardGamesByUserCallback {
            override fun onGetBoardGamesByUserCallbackSuccess(listUserBGGModel: ListUserBGGModel) {
                runBlocking {
                    loadingMutableLiveData.postValue(false)
                    getListUserBGGModel = listUserBGGModel
                    getListUserBGGModel.userBGG = userBGG
                    getAllThingFromList()
                    val listThingsBGGRoomEntity = appDatabase.ListThingsBGGDao().findByUserBGG(userBGG)
                    if (listThingsBGGRoomEntity == null || listThingsBGGRoomEntity.dateUpdate != listUserBGGModel.dateUpdate) {
                        appDatabase.ListThingsBGGDao().insert(ListUserBGGMapperBBDD().toBBDD(listUserBGGModel))
                    }
                }
            }

            override fun onGetBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBoardGamesByUserCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        }, userBGG)
    }

    private fun getAllThingFromList() {
        runBlocking {
            var stringBuilderIds = StringBuilder("")
            var findByThingId: ThingBGGRoomEntity
            getListUserBGGModel.listThings.forEach { objectId ->
                findByThingId = appDatabase.ThingBGGDao().findByThingId(objectId.toInt())
                if (findByThingId == null || timeStampNow - findByThingId.dateUpdate > ONE_MONTH_IN_MILLISECOND) {
                    stringBuilderIds.append(objectId)
                    stringBuilderIds.append(",")
                }
            }
            stringBuilderIds.dropLast(1)
            Log.i(TAG, "l> Vamos a pedir: ${stringBuilderIds.toString()}")
            val idsToDownload = stringBuilderIds.toString()
            if (!idsToDownload.isNullOrBlank()) {
                getThingsBoardGamesGeek(idsToDownload)
            } else {
                Log.i(TAG, "l> No hay que descargar nada, ya lo tenemos todo :-)")
                loadingMutableLiveData.postValue(false)
            }
        }
    }

    private fun getThingsBoardGamesGeek(things: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getThingsBoardGameGeek(object : DataSourceCallbacks.GetThingsBoardGamesGeekCallback {

            override fun onGetThingsBoardGamesGeekCallbackSuccess(listThingBGGModels: ArrayList<ThingBGGModel>) {
                runBlocking {
                    loadingMutableLiveData.postValue(false)
                    val listThingsBGGRoomEntity: ArrayList<ThingBGGRoomEntity> = ArrayList()
                    val thingBGGMapperBBDD: ThingBGGMapperBBDD = ThingBGGMapperBBDD()
                    listThingBGGModels.forEach { thingBGGModel ->
                        listThingsBGGRoomEntity.add(thingBGGMapperBBDD.toBBDD(thingBGGModel))
                    }
                    appDatabase.ThingBGGDao().insertAll(*listThingsBGGRoomEntity.toTypedArray())
                }
            }

            override fun onGetThingsBoardGamesGeekCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetThingsBoardGamesGeekCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        }, things)
    }
}