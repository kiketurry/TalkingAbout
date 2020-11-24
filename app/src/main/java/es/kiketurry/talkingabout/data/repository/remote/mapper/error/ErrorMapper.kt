package es.kiketurry.talkingabout.data.repository.remote.mapper.error

import es.kiketurry.talkingabout.data.domain.model.error.ErrorModel
import es.kiketurry.talkingabout.data.repository.remote.mapper.RequestMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.error.ErrorResponse

class ErrorMapper : RequestMapper<ErrorModel, ErrorResponse>, ResponseMapper<ErrorResponse, ErrorModel> {
    override fun toRequest(model: ErrorModel): ErrorResponse {
        return ErrorResponse(model.message)
    }

    override fun fromResponse(response: ErrorResponse): ErrorModel {
        return ErrorModel(response.message)
    }
}