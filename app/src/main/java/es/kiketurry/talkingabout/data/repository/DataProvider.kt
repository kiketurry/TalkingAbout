package es.kiketurry.talkingabout.data.repository

class DataProvider private constructor(private val remoteDataSource: DataSource) : DataSource {
    companion object {
        var INSTANCE: DataProvider? = null

        @Synchronized
        fun getInstance(remoteDataSource: DataSource): DataProvider {
            if (INSTANCE == null) {
                INSTANCE = DataProvider(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int) {
        remoteDataSource.getBreeds(getBreedsCallback, quantity)
    }

    override fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String) {
        remoteDataSource.getPhotos(getBreedsPhotosCallback, breedId)
    }
}