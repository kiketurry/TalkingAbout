package es.kiketurry.talkingabout.data.repository.remote

import android.util.Log
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacks
import es.kiketurry.talkingabout.data.repository.remote.mapper.bgg.ListUserBGGMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.bgg.ThingBGGMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.breeds.BreedMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.breeds.BreedPhotoMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedPhotoResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedResponse
import es.kiketurry.talkingabout.utils.ErrorsUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallbacks {
    companion object {
        private val TAG: String? = ErrorsUtils::class.simpleName

        //****
        //CATS
        //****
        fun getBreedsCallback(getBreedsCallback: DataSourceCallbacks.GetBreedsCallback): Callback<ArrayList<BreedResponse>> {
            return object : Callback<ArrayList<BreedResponse>> {
                override fun onResponse(call: Call<ArrayList<BreedResponse>>, response: Response<ArrayList<BreedResponse>>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getBreedsCallback.")
                        var breedsModelList: ArrayList<BreedModel> = ArrayList()
                        var breedMapper: BreedMapper = BreedMapper()
                        for (breedResponse in response.body()!!) {
                            breedsModelList.add(breedMapper.fromResponse(breedResponse))
                        }
                        getBreedsCallback.onGetBreedsCallbackSuccess(breedsModelList)
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getBreedsCallback.")
                        getBreedsCallback.onGetBreedsCallbackUnsuccess(ErrorsUtils.generateErrorModelFromResponseErrorBody(response.errorBody()))
                    }
                }

                override fun onFailure(call: Call<ArrayList<BreedResponse>>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getBreedsCallback failure.")
                    getBreedsCallback.onGetBreedsCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }

        fun getPhotosCallback(getBreedsPhotosCallback: DataSourceCallbacks.GetBreedsPhotosCallback): Callback<ArrayList<BreedPhotoResponse>> {
            return object : Callback<ArrayList<BreedPhotoResponse>> {
                override fun onResponse(call: Call<ArrayList<BreedPhotoResponse>>, response: Response<ArrayList<BreedPhotoResponse>>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getPhotosCallback.")
                        var breedPhotoModelList: ArrayList<BreedPhotoModel> = ArrayList()
                        var breedPhoMapper: BreedPhotoMapper = BreedPhotoMapper()
                        for (breedPhotoResponse in response.body()!!) {
                            breedPhotoModelList.add(breedPhoMapper.fromResponse(breedPhotoResponse))
                        }

                        getBreedsPhotosCallback.onGetBreedsPhotosCallbacksSuccess(breedPhotoModelList)
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getPhotosCallback.")
                        getBreedsPhotosCallback.onGetBreedsPhotosCallbackUnsuccess(
                            ErrorsUtils.generateErrorModelFromResponseErrorBody(
                                response.errorBody()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ArrayList<BreedPhotoResponse>>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getPhotosCallback failure.")
                    getBreedsPhotosCallback.onGetBreedsPhotosCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }

        //***
        //BGG
        //***
        fun getListBoardGamesByUserCallback(getListBoardGamesByUserCallback: DataSourceCallbacks.GetListBoardGamesByUserCallback): Callback<ListBoardGameUserResponse> {
            return object : Callback<ListBoardGameUserResponse> {
                override fun onResponse(call: Call<ListBoardGameUserResponse>, response: Response<ListBoardGameUserResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getListBoardGamesByUserCallback.")
                        getListBoardGamesByUserCallback.onGetListBoardGamesByUserCallbackSuccess(ListUserBGGMapper().fromResponse(response.body()!!))
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getListBoardGamesByUserCallback.")
                        getListBoardGamesByUserCallback.onGetListBoardGamesByUserCallbackUnsuccess(
                            ErrorsUtils.generateErrorModelFromResponseErrorBody(
                                response.errorBody()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ListBoardGameUserResponse>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getListBoardGamesByUserCallback failure.")
                    getListBoardGamesByUserCallback.onGetListBoardGamesByUserCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }

        fun getThingsBoardGameGeekCallback(getThingsBoardGamesGeekCallback: DataSourceCallbacks.GetThingsBoardGamesGeekCallback): Callback<ListThingsBoardGameGeekResponse> {
            return object : Callback<ListThingsBoardGameGeekResponse> {
                override fun onResponse(call: Call<ListThingsBoardGameGeekResponse>, response: Response<ListThingsBoardGameGeekResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getThingsBoardGameGeekCallback.")
                        val listThingBGGModels: ArrayList<ThingBGGModel> = ArrayList()
                        val thingBGGMapper = ThingBGGMapper()
                        response.body()!!.boardGamesList?.forEachIndexed { _, thingBoardGameGeekResponse ->
                            listThingBGGModels.add(thingBGGMapper.fromResponse(thingBoardGameGeekResponse))
                        }
                        getThingsBoardGamesGeekCallback.onGetThingsBoardGamesGeekCallbackSuccess(listThingBGGModels)
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getThingsBoardGameGeekCallback.")
                        getThingsBoardGamesGeekCallback.onGetThingsBoardGamesGeekCallbackUnsuccess(
                            ErrorsUtils.generateErrorModelFromResponseErrorBody(
                                response.errorBody()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ListThingsBoardGameGeekResponse>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getThingsBoardGameGeekCallback failure.")
                    getThingsBoardGamesGeekCallback.onGetThingsBoardGamesGeekCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }
    }
}