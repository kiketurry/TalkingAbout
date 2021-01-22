package es.kiketurry.talkingabout.data.repository

interface DataSource {
    //CATS
    fun getBreeds(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback, quantity: Int)
    fun getPhotos(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback, breedId: String)

    //BGG
    fun getBoardGamesByUser(getListBoardGamesByUserCallback: DataSourceCallbacks.GetListBoardGamesByUserCallback, user: String)
    fun getThingsBoardGameGeek(getThingsBoardGamesGeekCallback: DataSourceCallbacks.GetThingsBoardGamesGeekCallback, things: String)
}