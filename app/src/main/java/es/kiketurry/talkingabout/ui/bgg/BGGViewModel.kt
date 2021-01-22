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
        const val TOTAL_FAILURE_PERMIT_BEFORE_FAILURE_GET_LIST_USER_BGG = 1
    }

    var userBGGSelectedMutableLiveData: MutableLiveData<String> = MutableLiveData()

    var getListUserBGGModel: ListUserBGGModel = ListUserBGGModel()
    var getListThings: ArrayList<ThingBGGModel> = ArrayList()

    var timeStampNow = Date().time

    var countFailureGetListUserBGG = 0
    var userSelectedBGG = ""

    fun setUserBGGSelected(userBGG: String) {
        userBGGSelectedMutableLiveData.postValue(userBGG)
        getListAndBoardGamesUser(userBGG)
    }

    private fun getListAndBoardGamesUser(userSelectedBGG: String) {
        loadingMutableLiveData.postValue(true)
        this.userSelectedBGG = userSelectedBGG
        getListBoardGamesByUser(userSelectedBGG)
    }

    private fun getListBoardGamesByUser(userBGG: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getBoardGamesByUser(object : DataSourceCallbacks.GetListBoardGamesByUserCallback {
            override fun onGetListBoardGamesByUserCallbackSuccess(listUserBGGModel: ListUserBGGModel) {
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

            override fun onGetListBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetListBoardGamesByUserCallbackFailure(errorModel: ErrorModel) {
                if (countFailureGetListUserBGG < TOTAL_FAILURE_PERMIT_BEFORE_FAILURE_GET_LIST_USER_BGG) {
                    getListBoardGamesByUser(userSelectedBGG)
                } else {
                    errorMutableLiveData.postValue(errorModel)
                    loadingMutableLiveData.postValue(false)
                }
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
            Log.d(TAG, "l> Vamos a pedir: $stringBuilderIds")
            val idsToDownload = stringBuilderIds.toString()
            if (!idsToDownload.isNullOrBlank()) {
                getThingsBoardGamesGeek(idsToDownload)
            } else {
                Log.d(TAG, "l> No hay que descargar nada, ya lo tenemos todo :-)")
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
                    val thingBGGMapperBBDD = ThingBGGMapperBBDD()
                    listThingBGGModels.forEach { thingBGGModel ->
                        listThingsBGGRoomEntity.add(thingBGGMapperBBDD.toBBDD(thingBGGModel))
                    }
                    appDatabase.ThingBGGDao().insertAll(*listThingsBGGRoomEntity.toTypedArray())
                    completeInformationTableListThingsBGGUser(getListUserBGGModel.userBGG)
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

    private fun completeInformationTableListThingsBGGUser(userBGG: String) {
        runBlocking {
            var totalBoardGame = 0
            var totalExpansion = 0
            val listUserBGGModel = ListUserBGGMapperBBDD().toModel(appDatabase.ListThingsBGGDao().findByUserBGG(userBGG))
            val listThingBGGModel = ThingBGGMapperBBDD().toModelListThingsEntity(
                appDatabase.ThingBGGDao().getAllThingsByIds(listUserBGGModel.listThings.toIntArray())
            )
            val totalCount = listThingBGGModel.size
            listThingBGGModel.forEach { thingBGGModel ->
                when (thingBGGModel.type) {
                    ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW -> {
                        Log.d(
                            TAG,
                            "l> tenemos un juego desconocido id: ${thingBGGModel.id} con nonmbre principal: ${thingBGGModel.nameFirst}"
                        )
                    }
                    ThingBGGModel.TypeThingBGG.TYPE_THING_BOARDGAME -> {
                        totalBoardGame++
                    }
                    ThingBGGModel.TypeThingBGG.TYPE_THING_EXPANSION -> {
                        totalExpansion++
                    }
                }
            }
            val listThingsBGGRoomEntity = appDatabase.ListThingsBGGDao().findByUserBGG(userBGG)
            listThingsBGGRoomEntity.totalThings = totalCount.toString()
            listThingsBGGRoomEntity.totalBoardGames = totalBoardGame.toString()
            listThingsBGGRoomEntity.totalExpansions = totalExpansion.toString()
            appDatabase.ListThingsBGGDao().update(listThingsBGGRoomEntity)
        }
    }
}