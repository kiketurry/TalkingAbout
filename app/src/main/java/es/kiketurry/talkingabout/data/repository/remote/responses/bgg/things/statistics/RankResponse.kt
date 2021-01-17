package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false)
class RankResponse @JvmOverloads constructor(
    @field: ElementList(entry = "rank", inline = true)
    var rankList: List<RankDataResponse>? = null,
)