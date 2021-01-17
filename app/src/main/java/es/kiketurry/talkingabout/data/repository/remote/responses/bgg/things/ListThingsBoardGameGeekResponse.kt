package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
class ListThingsBoardGameGeekResponse @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var boardGamesList: List<ThingBoardGameGeekResponse>? = null
)