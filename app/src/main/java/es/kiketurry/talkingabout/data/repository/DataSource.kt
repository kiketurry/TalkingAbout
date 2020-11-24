package es.kiketurry.talkingabout.data.repository

interface DataSource {
    fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int)

    fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String)
}