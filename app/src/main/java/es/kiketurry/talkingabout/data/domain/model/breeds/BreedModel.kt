package es.kiketurry.talkingabout.data.domain.model.breeds

import es.kiketurry.talkingabout.data.domain.model.BaseModel

class BreedModel(
    var id: String,
    var name: String,
    var description: String,
    var adaptability: Int,
    var intelligence: Int,
    var affectionLevel: Int,
    var childFriendly: Int,
    var dogFriendly: Int,
    var photoList: ArrayList<String>,
) : BaseModel() {
    constructor(
        id: String,
        name: String,
        description: String,
        adaptability: Int,
        intelligence: Int,
        affectionLevel: Int,
        childFriendly: Int,
        dogFriendly: Int
    ) : this(id, name, description, adaptability, intelligence, affectionLevel, childFriendly, dogFriendly, ArrayList())
}