package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class PollResponse @JvmOverloads constructor(
    @field: Attribute(name = "name")
    var namePoll: String? = null,
    @field: Attribute
    var title: String? = null,
    @field: Attribute
    var totalvotes: String? = null,
    @field: ElementList(entry = "results", inline = true)
    var results: List<PollResultResponse>? = null,
)