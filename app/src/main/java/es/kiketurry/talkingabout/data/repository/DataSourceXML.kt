package es.kiketurry.talkingabout.data.repository

interface DataSourceXML {
    fun getBoardGamesByUser(getBoardGamesByUserCallback: DataSourceCallbacksXML.GetBoardGamesByUserCallback, user: String)
    fun getThingsBoardGameGeek(getThingsBoardGamesGeekCallback: DataSourceCallbacksXML.GetThingsBoardGamesGeekCallback, things: String)
}