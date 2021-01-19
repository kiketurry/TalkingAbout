package es.kiketurry.talkingabout.data.repository

import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse

interface DataSourceCallbacks {
    //CATS
    interface GetBreedsCallback {
        fun onGetBreedsCallbackSuccess(breedsModelList: ArrayList<BreedModel>)

        fun onGetBreedsCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetBreedsCallbackFailure(errorModel: ErrorModel)
    }

    interface GetBreedsPhotosCallback {
        fun onGetBreedsPhotosCallbacksSuccess(photoList: ArrayList<BreedPhotoModel>)

        fun onGetBreedsPhotosCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetBreedsPhotosCallbackFailure(errorModel: ErrorModel)
    }

    //BGG
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