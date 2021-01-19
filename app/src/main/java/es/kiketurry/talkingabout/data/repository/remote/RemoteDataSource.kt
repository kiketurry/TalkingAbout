package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.DataSource
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks

class RemoteDataSource(private val apiServiceCats: ApiServicesCats, private val apiServiceBGG: ApiServicesBGG) : DataSource {
    companion object {
        var INSTANCE: RemoteDataSource? = null

        @Synchronized
        fun getInstance(apiServiceCats: ApiServicesCats, apiServiceBGG: ApiServicesBGG): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiServiceCats, apiServiceBGG)
            }
            return INSTANCE!!
        }
    }

    //CATS
    override fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int) {
        var getBreedsCall = apiServiceCats.getBreeds(quantity)
        getBreedsCall.enqueue(RetrofitCallbacks.getBreedsCallback(getBreedsCallback))
    }

    override fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String) {
        var getBreedImagesCall = apiServiceCats.getImages(breedId, "RANDOM", 50)
        getBreedImagesCall.enqueue(RetrofitCallbacks.getPhotosCallback(getBreedsPhotosCallback))
    }

    //BGG
    override fun getBoardGamesByUser(getBoardGamesByUserCallback: DataSourceCallbacks.GetBoardGamesByUserCallback, user: String) {
        val getBoardGameByUserCall = apiServiceBGG.getListBoardGameUser(user, 1, "boardgameexpansion")
        getBoardGameByUserCall.enqueue(RetrofitCallbacks.getBoardGamesByUserCallback(getBoardGamesByUserCallback))
    }

    override fun getThingsBoardGameGeek(
        getThingsBoardGamesGeekCallback: DataSourceCallbacks.GetThingsBoardGamesGeekCallback,
        things: String
    ) {
        val getThingsBoardGameGeekCall = apiServiceBGG.getThingBoardGameGeek(things, 1)
        getThingsBoardGameGeekCall.enqueue(RetrofitCallbacks.getThingsBoardGameGeekCallback(getThingsBoardGamesGeekCallback))
    }
}