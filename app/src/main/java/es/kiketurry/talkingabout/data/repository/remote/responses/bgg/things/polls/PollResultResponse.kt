package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class PollResultResponse @JvmOverloads constructor(
    @field: Attribute(required = false)
    var numplayers: String? = null,
    @field: ElementList(entry = "result", inline = true)
    var pollResultDetailResponseList: List<PollResultDetailResponse>? = null,
)