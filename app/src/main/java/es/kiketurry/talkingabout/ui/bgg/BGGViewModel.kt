package es.kiketurry.talkingabout.ui.bgg

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.mlkit.nl.languageid.LanguageIdentification
import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ListUserBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingNameEsBGGMapperBBDD
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
        const val TIME_IN_MILLISECOND_WAIT_UPDATE_DATA_BGG = 5000L
    }

    var userBGGSelectedMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var loadingDataBGGMutableLiveData: MutableLiveData<Int> = MutableLiveData()

    var thingBGGSelectedMutableLiveData: MutableLiveData<ThingBGGModel> = MutableLiveData()

    var getListUserBGGModel: ListUserBGGModel = ListUserBGGModel()
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
                    getListUserBGGModel = listUserBGGModel
                    getListUserBGGModel.userBGG = userBGG
                    val listThingsBGGRoomEntity = appDatabase.ListThingsBGGDao().findByUserBGG(userBGG)
                    if (listThingsBGGRoomEntity == null || listThingsBGGRoomEntity.dateUpdate != listUserBGGModel.dateUpdate) {
                        appDatabase.ListThingsBGGDao().insert(ListUserBGGMapperBBDD().toBBDD(listUserBGGModel))
                    }

                    appDatabase.ThingUserBGGDao().insertAll(
                        *ListUserBGGMapperBBDD().getListThingUserBGGRoomEntity(listUserBGGModel.listThings, userBGG).toTypedArray()
                    )
                    getAllThingUser()
                }
            }

            override fun onGetListBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetListBoardGamesByUserCallbackFailure(errorModel: ErrorModel) {
                if (countFailureGetListUserBGG < TOTAL_FAILURE_PERMIT_BEFORE_FAILURE_GET_LIST_USER_BGG) {
                    countFailureGetListUserBGG++
                    getListBoardGamesByUser(userSelectedBGG)
                } else {
                    errorMutableLiveData.postValue(errorModel)
                    loadingMutableLiveData.postValue(false)
                }
            }
        }, userBGG)
    }

    private fun getAllThingUser() {
        runBlocking {
            val stringBuilderIds = StringBuilder("")
            val allThingsUserByUserBGG = appDatabase.ThingUserBGGDao().getAllThingsUserByUserBGG(getListUserBGGModel.userBGG)
            val timeStampMap = ThingBGGMapperBBDD().getTimeStampMap(appDatabase.ThingBGGDao().getAllThings())
            allThingsUserByUserBGG.forEach { thingUserEntity ->
                if (!timeStampMap.containsKey(thingUserEntity.thingId) || timeStampNow - timeStampMap[thingUserEntity.thingId]!! > ONE_MONTH_IN_MILLISECOND) {
                    stringBuilderIds.append(thingUserEntity.thingId)
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
                loadingDataBGGMutableLiveData.postValue(0)
            }
        }
    }

    private fun getThingsBoardGamesGeek(things: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getThingsBoardGameGeek(object : DataSourceCallbacks.GetThingsBoardGamesGeekCallback {

            override fun onGetThingsBoardGamesGeekCallbackSuccess(listThingBGGModels: ArrayList<ThingBGGModel>) {
                runBlocking {
                    val mapTranslate = ThingNameEsBGGMapperBBDD().getMapTranslate(appDatabase.ThingNameEsBGGDao().getAllThingsNameEs())
                    val listThingsBGGRoomEntity: ArrayList<ThingBGGRoomEntity> = ArrayList()
                    val thingBGGMapperBBDD = ThingBGGMapperBBDD()
                    listThingBGGModels.forEach { thingBGGModel ->
                        if (mapTranslate.containsKey(thingBGGModel.thingId)) {
                            thingBGGModel.nameEs = mapTranslate[thingBGGModel.thingId].toString()
                        }
                        listThingsBGGRoomEntity.add(thingBGGMapperBBDD.toBBDD(thingBGGModel))
                    }
                    appDatabase.ThingBGGDao().insertAll(*listThingsBGGRoomEntity.toTypedArray())
                    completeInformationTypeThingListThingsBGGUser(getListUserBGGModel.userBGG)
                    loadingMutableLiveData.postValue(false)
                    loadingDataBGGMutableLiveData.postValue(0)
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

    private fun completeInformationTypeThingListThingsBGGUser(userBGG: String) {
        runBlocking {
            var totalBoardGame = 0
            var totalExpansion = 0
            val listThingBGGModel = ThingBGGMapperBBDD().toModelListThingsEntity(appDatabase.JoinsBGGDao().getAllThingsUser(userBGG))
            val totalCount = listThingBGGModel.size
            listThingBGGModel.forEach { thingBGGModel ->
                when (thingBGGModel.type) {
                    ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW -> {
                        Log.d(TAG, "l> Juego desconocido id: ${thingBGGModel.thingId} con nombre: ${thingBGGModel.nameFirst}")
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

    private fun searchSpanishNames() {
        runBlocking {
            val languageIdentifier = LanguageIdentification.getClient()
            val thingBGGMapperBBDD = ThingBGGMapperBBDD()
            val listThingBGGModel: ArrayList<ThingBGGModel> =
                thingBGGMapperBBDD.toModelListThingsEntity(appDatabase.ThingBGGDao().getAllThings())
            var confidenceMax: Float
            var confidenceUpdate: Float
            var probablySpanish: String

            Log.d(TAG, "l> Vamos a analizar el idioma de ${listThingBGGModel.size} juegos")

            listThingBGGModel.forEach { thingBGGModel ->

                Log.d(TAG, "l> Juego ${thingBGGModel.nameFirst}")

                confidenceMax = 0.0F
                confidenceUpdate = 0.0F
                probablySpanish = ""
                thingBGGModel.nameList.forEach { name ->

                    Log.d(TAG, "l> Posible nombre $name")

                    languageIdentifier.identifyPossibleLanguages(name)
                        .addOnSuccessListener { identifiedLanguages ->
                            for (identifiedLanguage in identifiedLanguages) {

                                Log.d(
                                    TAG,
                                    "l> idioma identificado: ${identifiedLanguage.languageTag}, confianza de nombre ${identifiedLanguage.confidence}, nombre juego: $name - confidenceMax: $confidenceMax"
                                )

                                if (identifiedLanguage.languageTag == "es" && identifiedLanguage.confidence > confidenceMax) {
                                    confidenceMax = identifiedLanguage.confidence
                                    probablySpanish = name
                                    Log.d(TAG, "l> nombre probable en español: $probablySpanish con confianza del $confidenceMax")
                                }
                            }

                            if (probablySpanish.isNotBlank() && confidenceMax >= 0.33F && confidenceMax > confidenceUpdate) {
                                thingBGGModel.nameEs = probablySpanish
                                runBlocking {
                                    appDatabase.ThingBGGDao().update(thingBGGMapperBBDD.toBBDD(thingBGGModel))
                                    Log.d(TAG, "l> actualizamos la base de datos con el nombre $name")
                                    confidenceUpdate = confidenceMax
                                    confidenceMax = 0.0F
                                    probablySpanish = ""
                                }
                            }
                        }
                }
            }
        }
    }

    fun setThingBGGSelected(thingBGGModel: ThingBGGModel) {
        runBlocking {
            thingBGGSelectedMutableLiveData.postValue(
                ThingBGGMapperBBDD().toModel(
                    appDatabase.ThingBGGDao().findByThingId(thingBGGModel.thingId)
                )
            )
        }
    }
}