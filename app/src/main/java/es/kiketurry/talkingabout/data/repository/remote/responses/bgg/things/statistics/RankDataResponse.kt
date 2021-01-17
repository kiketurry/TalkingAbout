package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false)
class RankDataResponse @JvmOverloads constructor(
    @field: Attribute(required = false)
    var friendlyname: String? = null,
    @field: Attribute(required = false, name = "value")
    var valueName: String? = null,
)