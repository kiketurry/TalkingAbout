package es.kiketurry.talkingabout.data.repository.remote.mapper.breeds

import es.kiketurry.talkingabout.data.domain.model.breeds.BreedModel
import es.kiketurry.talkingabout.data.repository.remote.mapper.RequestMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedResponse

class BreedMapper : RequestMapper<BreedModel, BreedResponse>, ResponseMapper<BreedResponse, BreedModel> {
    override fun toRequest(model: BreedModel): BreedResponse {
        return BreedResponse(
            model.id,
            model.name,
            model.description,
            model.adaptability,
            model.intelligence,
            model.affectionLevel,
            model.childFriendly,
            model.dogFriendly
        )
    }

    override fun fromResponse(response: BreedResponse): BreedModel {
        return BreedModel(
            response.id,
            response.name,
            response.description,
            response.adaptability,
            response.intelligence,
            response.affectionLevel,
            response.childFriendly,
            response.dogFriendly
        )
    }
}