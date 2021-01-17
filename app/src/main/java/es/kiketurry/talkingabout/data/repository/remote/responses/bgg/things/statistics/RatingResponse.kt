package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics

import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.DataValueResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
class RatingResponse @JvmOverloads constructor(
    @field: Element(name = "ranks")
    var ranks: RankResponse = RankResponse(),
    @field: Element(name = "averageweight")
    var averageweight: DataValueResponse = DataValueResponse(),
)