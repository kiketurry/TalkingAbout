package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.DataSource
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.injection.holder.SingletonHolderOneParameter

class RemoteDataSource(private val apiService: ApiServices) : DataSource {
    init {

    }

    companion object : SingletonHolderOneParameter<RemoteDataSource, ApiServices>(::RemoteDataSource)

    override fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int) {
        var getBreedsCall = apiService.getBreeds(quantity)
        getBreedsCall.enqueue(RetrofitCallbacks.getBreedsCallback(getBreedsCallback))
    }

    override fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String) {
        var getBreedImagesCall = apiService.getImages(breedId, "RANDOM", 50)
        getBreedImagesCall.enqueue(RetrofitCallbacks.getPhotosCallback(getBreedsPhotosCallback))
    }
}