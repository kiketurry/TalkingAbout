package es.kiketurry.talkingabout.data.repository.bbdd.mapper

interface BBDDMapperModel<M, B> {
    fun toBBDD(model: M): B
    fun toModel(bbdd: B): M
}