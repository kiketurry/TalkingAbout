package es.kiketurry.talkingabout.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.mlkit.nl.languageid.LanguageIdentification
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataProviderXML
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks.GetBreedsCallback
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacksXML
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse
import es.kiketurry.talkingabout.ui.base.BaseViewModel

class MainViewModel(application: Application, private val dataProvider: DataProvider, private val dataProviderXML: DataProviderXML) :
    BaseViewModel(application) {
    override val TAG: String? get() = MainViewModel::class.qualifiedName

    var breedsModelListMutableLiveData: MutableLiveData<ArrayList<BreedModel>> = MutableLiveData()
    var breedCatSelectedMutableLiveData: MutableLiveData<BreedModel> = MutableLiveData()

    lateinit var breedCatSelected: BreedModel
    var breedsModelArrayList: ArrayList<BreedModel> = ArrayList()

    fun getBreeds(limitBreeds: Int) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getBreeds(object : GetBreedsCallback {
            override fun onGetBreedsCallbackSuccess(breedsModelList: ArrayList<BreedModel>) {
                breedsModelArrayList = breedsModelList
                breedsModelListMutableLiveData.postValue(breedsModelArrayList)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBreedsCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBreedsCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        }, limitBreeds)
    }

    fun getBoardGamesByUser(user: String) {
        loadingMutableLiveData.postValue(true)
        dataProviderXML.getBoardGamesByUser(object : DataSourceCallbacksXML.GetBoardGamesByUserCallback {

            override fun onGetBoardGamesByUserCallbackSuccess(listBoardGameUserResponse: ListBoardGameUserResponse) {
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBoardGamesByUserCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        }, user)
    }

    fun getThingsBoardGamesGeek(things: String) {
        loadingMutableLiveData.postValue(true)
        dataProviderXML.getThingsBoardGameGeek(object : DataSourceCallbacksXML.GetThingsBoardGamesGeekCallback {
            override fun onGetThingsBoardGamesGeekCallbackSuccess(listThingsBoardGameGeekResponse: ListThingsBoardGameGeekResponse) {
                loadingMutableLiveData.postValue(false)
                listThingsBoardGameGeekResponse.boardGamesList?.get(0)?.namesList?.forEachIndexed { index, nameBoardGameGeekResponse ->
                    nameBoardGameGeekResponse.nameThing?.let {
                        detectLanguage(it)
//                        posibleLanguage(it)
                    }
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

    private fun getPhothos(breedId: String) {
        loadingMutableLiveData.postValue(true)
        dataProvider.getPhotos(object : DataSourceCallbacks.GetBreedsPhotosCallback {
            override fun onGetBreedsPhotosCallbacksSuccess(photoList: ArrayList<BreedPhotoModel>) {
                breedCatSelected.photoList = BreedPhotoModel.breedsPhotoModelArrayListToStringArrayList(photoList)
                breedCatSelectedMutableLiveData.postValue(breedCatSelected)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBreedsPhotosCallbackUnsuccess(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }

            override fun onGetBreedsPhotosCallbackFailure(errorModel: ErrorModel) {
                errorMutableLiveData.postValue(errorModel)
                loadingMutableLiveData.postValue(false)
            }
        }, breedId)
    }

    fun setBreedModelSelected(position: Int) {
        breedCatSelected = breedsModelArrayList[position]
        getPhothos(breedCatSelected.id)
    }

}