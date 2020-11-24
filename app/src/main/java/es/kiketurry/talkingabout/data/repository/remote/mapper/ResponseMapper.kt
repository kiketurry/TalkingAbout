package es.kiketurry.talkingabout.data.repository.remote.mapper

interface ResponseMapper<E, M> {
    fun fromResponse(response: E) : M
}