package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false)
class StatisticsResponse @JvmOverloads constructor(
    @field: Element(name = "ratings")
    var ratings: RatingResponse = RatingResponse(),
)