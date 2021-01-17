package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things

import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls.PollResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics.StatisticsResponse
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class ThingBoardGameGeekResponse @JvmOverloads constructor(
    @field: Attribute(name = "type")
    var type: String = "",
    @field: Element(name = "thumbnail")
    var thumbnail: String = "",
    @field: Element(name = "image")
    var image: String = "",
    @field: ElementList(entry = "name", inline = true)
    var namesList: List<NameBoardGameGeekResponse>? = null,
    @field: Element(name = "description")
    var description: String = "",
    @field: Element(name = "yearpublished")
    var yearpublished: DataValueResponse = DataValueResponse(),
    @field: Element(name = "minplayers")
    var minplayers: DataValueResponse = DataValueResponse(),
    @field: Element(name = "maxplayers")
    var maxplayers: DataValueResponse = DataValueResponse(),
    @field: Element(name = "playingtime")
    var playingtime: DataValueResponse = DataValueResponse(),
    @field: Element(name = "minplaytime")
    var minplaytime: DataValueResponse = DataValueResponse(),
    @field: Element(name = "maxplaytime")
    var maxplaytime: DataValueResponse = DataValueResponse(),
    @field: Element(name = "minage")
    var minage: DataValueResponse = DataValueResponse(),
    @field: ElementList(entry = "poll", inline = true)
    var pollList: List<PollResponse>? = null,
    @field: Element(name = "statistics", required = false)
    var statistics: StatisticsResponse = StatisticsResponse(),
)