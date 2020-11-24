package es.kiketurry.talkingabout.data.domain.model.error

import es.kiketurry.talkingabout.data.domain.model.BaseModel

class ErrorModel(var type: Int, var code: Int, var title: String, var message: String) : BaseModel() {
    constructor(message: String) : this(0, 0, "", message)
    constructor() : this("")
}