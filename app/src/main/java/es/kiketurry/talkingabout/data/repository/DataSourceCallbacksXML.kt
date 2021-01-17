package es.kiketurry.talkingabout.data.repository

import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse

interface DataSourceCallbacksXML {
    interface GetBoardGamesByUserCallback {
        fun onGetBoardGamesByUserCallbackSuccess(listBoardGameUserResponse: ListBoardGameUserResponse)

        fun onGetBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetBoardGamesByUserCallbackFailure(errorModel: ErrorModel)
    }

    interface GetThingsBoardGamesGeekCallback {
        fun onGetThingsBoardGamesGeekCallbackSuccess(listThingsBoardGameGeekResponse: ListThingsBoardGameGeekResponse)

        fun onGetThingsBoardGamesGeekCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetThingsBoardGamesGeekCallbackFailure(errorModel: ErrorModel)
    }
}