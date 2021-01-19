package es.kiketurry.talkingabout.data.repository

interface DataSource {
    //CATS
    fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int)
    fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String)

    //BGG
    fun getBoardGamesByUser(getBoardGamesByUserCallback: DataSourceCallbacks.GetBoardGamesByUserCallback, user: String)
    fun getThingsBoardGameGeek(getThingsBoardGamesGeekCallback: DataSourceCallbacks.GetThingsBoardGamesGeekCallback, things: String)
}