package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false)
class PollResultDetailResponse @JvmOverloads constructor(
    @field: Attribute(name = "value", required = false)
    var dataValue: String? = null,
    @field: Attribute(required = false)
    var numvotes: String? = null,
    @field: Attribute(required = false)
    var level: String? = null,
)