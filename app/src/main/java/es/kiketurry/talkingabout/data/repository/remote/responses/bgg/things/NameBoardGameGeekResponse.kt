package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class NameBoardGameGeekResponse @JvmOverloads constructor(
    @field: Attribute
    var type: String? = null,
    @field: Attribute(name = "value")
    var nameThing: String? = null,
)