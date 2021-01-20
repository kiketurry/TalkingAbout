package es.kiketurry.talkingabout.ui.bgg.listboardgames

import android.app.Application
import android.util.Log
import com.google.mlkit.nl.languageid.LanguageIdentification
import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.bbdd.AppDatabase
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ListUserBGGMapperBBDD
import es.kiketurry.talkingabout.data.repository.bbdd.mapper.bgg.ThingBGGMapperBBDD
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class ListBoardGamesBGGViewModel(application: Application, var appDatabase: AppDatabase, val dataProvider: DataProvider) :
    BaseViewModel(application) {

    override val TAG: String? get() = ListBoardGamesBGGViewModel::class.qualifiedName

    val listThingsBGGMapperBBDD = ListUserBGGMapperBBDD()
    val thingsBGGMapperBBDD = ThingBGGMapperBBDD()

    var getListUserBGGModel: ListUserBGGModel = ListUserBGGModel()
    var getListThings: ArrayList<ThingBGGModel> = ArrayList()

    fun userSelectedBGG(userSelectedBGG: String) {
        getListAndBoardGamesUser(userSelectedBGG)
    }

    private fun getListAndBoardGamesUser(userSelectedBGG: String) {
        loadingMutableLiveData.postValue(true)
        getBoardGamesByUser(userSelectedBGG)
    }

    private fun getBoardGamesByUser(userBGG: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getBoardGamesByUser(object : DataSourceCallbacks.GetBoardGamesByUserCallback {

            override fun onGetBoardGamesByUserCallbackSuccess(listUserBGGModel: ListUserBGGModel) {
                loadingMutableLiveData.postValue(false)
                getListUserBGGModel = listUserBGGModel
                getListUserBGGModel.userBGG = userBGG
                getAllThingFromList()
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
        var stringBuilderIds = StringBuilder("")
        getListUserBGGModel.listThings.forEachIndexed { index, objectId ->
            stringBuilderIds.append(objectId)
            if (index != getListUserBGGModel.listThings.size - 1) {
                stringBuilderIds.append(",")
            }
        }
        getThingsBoardGamesGeek(stringBuilderIds.toString())
    }

    private fun getThingsBoardGamesGeek(things: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getThingsBoardGameGeek(object : DataSourceCallbacks.GetThingsBoardGamesGeekCallback {

            override fun onGetThingsBoardGamesGeekCallbackSuccess(listThingBGGModels: ArrayList<ThingBGGModel>) {
                loadingMutableLiveData.postValue(false)
                getListThings = listThingBGGModels
                Log.i(TAG, "l> descargando juegos.....")
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

    fun detectLanguage(text: String) {
        val languageIdentifier = LanguageIdentification.getClient()
        languageIdentifier.identifyLanguage(text)
            .addOnSuccessListener { languageCode ->
                if (languageCode == "und") {
                    Log.i(TAG, "l> Can't identify language, $text")
                } else {
                    Log.i(TAG, "l> Language, $text -> $languageCode")
                }
            }
            .addOnFailureListener {
                Log.i(TAG, "l> detectLanguage error con el texto, $text: ${it.message}")
            }
    }

    fun posibleLanguage(text: String) {
        val languageIdentifier = LanguageIdentification.getClient()
        languageIdentifier.identifyPossibleLanguages(text)
            .addOnSuccessListener { identifiedLanguages ->
                for (identifiedLanguage in identifiedLanguages) {
                    val language = identifiedLanguage.languageTag
                    val confidence = identifiedLanguage.confidence
                    Log.i(TAG, "l> posible lenguaje: $language confianza de: $confidence")
                }
            }
            .addOnFailureListener {
                Log.i(TAG, "l> posibleLanguage error con el texto, $text: ${it.message}")
            }
    }

}