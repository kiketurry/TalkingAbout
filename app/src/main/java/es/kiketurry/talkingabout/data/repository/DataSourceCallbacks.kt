package es.kiketurry.talkingabout.data.repository

import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel

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
    interface GetListBoardGamesByUserCallback {
        fun onGetListBoardGamesByUserCallbackSuccess(listUserBGGModel: ListUserBGGModel)

        fun onGetListBoardGamesByUserCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetListBoardGamesByUserCallbackFailure(errorModel: ErrorModel)
    }

    interface GetThingsBoardGamesGeekCallback {
        fun onGetThingsBoardGamesGeekCallbackSuccess(listThingBGGModels: ArrayList<ThingBGGModel>)

        fun onGetThingsBoardGamesGeekCallbackUnsuccess(errorModel: ErrorModel)

        fun onGetThingsBoardGamesGeekCallbackFailure(errorModel: ErrorModel)
    }
}