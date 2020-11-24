package es.kiketurry.talkingabout.data.repository

import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel

interface DataSourceCallbacks {
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
}