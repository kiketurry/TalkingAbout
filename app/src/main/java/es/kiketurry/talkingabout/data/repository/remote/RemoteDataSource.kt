package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.DataSource
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks

class RemoteDataSource(private val apiService: ApiServices) : DataSource {
    companion object {
        var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(apiService: ApiServices): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiService)
            }
            return INSTANCE!!
        }
    }

    override fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int) {
        var getBreedsCall = apiService.getBreeds(quantity)
        getBreedsCall.enqueue(RetrofitCallbacks.getBreedsCallback(getBreedsCallback))
    }

    override fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String) {
        var getBreedImagesCall = apiService.getImages(breedId, "RANDOM", 50)
        getBreedImagesCall.enqueue(RetrofitCallbacks.getPhotosCallback(getBreedsPhotosCallback))
    }
}