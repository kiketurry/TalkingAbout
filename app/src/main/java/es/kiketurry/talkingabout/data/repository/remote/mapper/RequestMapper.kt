package es.kiketurry.talkingabout.data.repository.remote.mapper

interface RequestMapper<M, E> {
    fun toRequest(model: M): E
}