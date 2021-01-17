package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false)
class DataValueResponse @JvmOverloads constructor(
    @field: Attribute
    var value: String? = null,
)