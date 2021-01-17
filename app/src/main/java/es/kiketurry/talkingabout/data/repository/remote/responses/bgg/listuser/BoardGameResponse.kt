package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class BoardGameResponse @JvmOverloads constructor(
    @field: Attribute
    var objectid: String? = null,
    @field: Attribute
    var subtype: String? = null,
    @field: Attribute
    var collid: String? = null,
    @field: Element(name = "name")
    var name: String = "",
    @field: Element(name = "yearpublished")
    var yearpublished: String = "",
    @field: Element(name = "image")
    var image: String = "",
    @field: Element(name = "thumbnail")
    var thumbnail: String = "",
    @field: Element(name = "status", required = false)
    var status: StatusBoardGameResponse = StatusBoardGameResponse(),
    @field: Element(name = "numplays")
    var numplays: String = ""
)