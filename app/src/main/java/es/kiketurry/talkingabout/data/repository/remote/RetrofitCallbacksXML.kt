package es.kiketurry.talkingabout.data.repository.remote

import android.util.Log
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.DataSourceCallbacksXML
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse
import es.kiketurry.talkingabout.utils.ErrorsUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallbacksXML {
    companion object {
        private val TAG: String? = ErrorsUtils::class.simpleName

        //****
        //BGG
        //****
        fun getBoardGamesByUserCallback(getBoardGamesByUserCallback: DataSourceCallbacksXML.GetBoardGamesByUserCallback): Callback<ListBoardGameUserResponse> {
            return object : Callback<ListBoardGameUserResponse> {
                override fun onResponse(call: Call<ListBoardGameUserResponse>, response: Response<ListBoardGameUserResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getBoardGmesByUserCallback.")
                        getBoardGamesByUserCallback.onGetBoardGamesByUserCallbackSuccess(response.body()!!)
                    } else {
                        Log.e(TAG, "l> Problemas en la respuesta de getBoardGmesByUserCallback.")
                        getBoardGamesByUserCallback.onGetBoardGamesByUserCallbackUnsuccess(
                            ErrorsUtils.generateErrorModelFromResponseErrorBody(
                                response.errorBody()
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<ListBoardGameUserResponse>, throwable: Throwable) {
                    Log.e(TAG, "l> Problemas en la respuesta de getBoardGmesByUserCallback failure.")
                    getBoardGamesByUserCallback.onGetBoardGamesByUserCallbackFailure(ErrorModel(throwable.message ?: "unknow"))
                }
            }
        }

        fun getThingsBoardGameGeekCallback(getThingsBoardGamesGeekCallback: DataSourceCallbacksXML.GetThingsBoardGamesGeekCallback): Callback<ListThingsBoardGameGeekResponse> {
            return object : Callback<ListThingsBoardGameGeekResponse> {
                override fun onResponse(call: Call<ListThingsBoardGameGeekResponse>, response: Response<ListThingsBoardGameGeekResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        Log.i(TAG, "l> Éxito en la respuesta de getThingsBoardGameGeekCallback.")
                        getThingsBoardGamesGeekCallback.onGetThingsBoardGamesGeekCallbackSuccess(response.body()!!)
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