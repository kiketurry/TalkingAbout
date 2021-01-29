package es.kiketurry.talkingabout.ui.cats

import android.app.Application
import androidx.lifecycle.MutableLiveData
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataProvider
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks.GetBreedsCallback
import es.kiketurry.talkingabout.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CatsViewModel(ioDispatcher: CoroutineDispatcher = Dispatchers.IO, application: Application, val dataProvider: DataProvider) :
    BaseViewModel(ioDispatcher, application) {
    override val TAG: String? get() = CatsViewModel::class.qualifiedName

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