package es.kiketurry.talkingabout.data.repository

class DataProviderXML private constructor(private val remoteDataSource: DataSourceXML) : DataSourceXML {
    companion object {
        var INSTANCE: DataProviderXML? = null

        @Synchronized
        fun getInstance(remoteDataSource: DataSourceXML): DataProviderXML {
            if (INSTANCE == null) {
                INSTANCE = DataProviderXML(remoteDataSource)
            }
            return INSTANCE!!
        }
    }

    override fun getBoardGamesByUser(getBoardGamesByUserCallback: DataSourceCallbacksXML.GetBoardGamesByUserCallback, user: String) {
        remoteDataSource.getBoardGamesByUser(getBoardGamesByUserCallback, user)
    }

    override fun getThingsBoardGameGeek(
        getThingsBoardGamesGeekCallback: DataSourceCallbacksXML.GetThingsBoardGamesGeekCallback,
        things: String
    ) {
        remoteDataSource.getThingsBoardGameGeek(getThingsBoardGamesGeekCallback, things)
    }
}