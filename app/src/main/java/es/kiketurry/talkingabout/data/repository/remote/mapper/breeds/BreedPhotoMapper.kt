package es.kiketurry.talkingabout.data.repository.remote.mapper.breeds

import es.kiketurry.talkingabout.data.domain.model.breeds.BreedPhotoModel
import es.kiketurry.talkingabout.data.repository.remote.mapper.RequestMapper
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedPhotoResponse

class BreedPhotoMapper : ResponseMapper<BreedPhotoResponse, BreedPhotoModel> {
    override fun fromResponse(response: BreedPhotoResponse): BreedPhotoModel {
        return BreedPhotoModel(response.url)
    }
}