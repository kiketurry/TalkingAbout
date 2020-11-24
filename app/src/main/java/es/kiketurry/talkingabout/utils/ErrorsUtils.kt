package es.kiketurry.talkingabout.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.remote.responses.error.ErrorResponse
import es.kiketurry.talkingabout.data.repository.remote.mapper.error.ErrorMapper
import okhttp3.ResponseBody

class ErrorsUtils {
    companion object {
        private val TAG: String? = ErrorsUtils::class.simpleName

        fun generateErrorModelFromResponseErrorBody(responseBody: ResponseBody?): ErrorModel {
            val errorModel: ErrorModel
            val gson = Gson()
            var errorResponse: ErrorResponse? = null
            try {
                errorResponse = gson.fromJson(responseBody?.charStream(), ErrorResponse::class.java)
            } catch (exception: JsonSyntaxException) {
                Log.e(TAG, "l> generateErrorModelFromResponseErrorBody problem gson: ${exception.message}")
            }

            if(errorResponse != null) {
                errorModel = ErrorMapper().fromResponse(errorResponse)
                errorModel.message = "Ups! Hemos sufrido un error, pero ya es tarde para arreglarlo, no???? Mejor vamos a dormir XD! Vuelve en un rato"
            } else {
                errorModel = ErrorModel()
            }

            return errorModel
        }
    }

}