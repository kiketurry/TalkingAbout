package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.DataSourceCallbacksXML
import es.kiketurry.talkingabout.data.repository.DataSourceXML

class RemoteDataSourceXML(private val apiServiceXML: ApiServicesXML) : DataSourceXML {
    companion object {
        var INSTANCE: RemoteDataSourceXML? = null

        @Synchronized
        fun getInstance(apiServiceXML: ApiServicesXML): RemoteDataSourceXML {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSourceXML(apiServiceXML)
            }
            return INSTANCE!!
        }
    }

    override fun getBoardGamesByUser(getBoardGamesByUserCallback: DataSourceCallbacksXML.GetBoardGamesByUserCallback, user: String) {
        val getBoardGameByUserCall = apiServiceXML.getListBoardGameUser(user, 1, "boardgameexpansion")
        getBoardGameByUserCall.enqueue(RetrofitCallbacksXML.getBoardGamesByUserCallback(getBoardGamesByUserCallback))
    }

    override fun getThingsBoardGameGeek(
        getThingsBoardGamesGeekCallback: DataSourceCallbacksXML.GetThingsBoardGamesGeekCallback,
        things: String
    ) {
        val getThingsBoardGameGeekCall = apiServiceXML.getThingBoardGameGeek(things, 1)
        getThingsBoardGameGeekCall.enqueue(RetrofitCallbacksXML.getThingsBoardGameGeekCallback(getThingsBoardGamesGeekCallback))
    }
}