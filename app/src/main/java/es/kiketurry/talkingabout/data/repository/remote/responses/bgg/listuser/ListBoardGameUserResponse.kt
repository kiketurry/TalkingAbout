package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items", strict = false)
class ListBoardGameUserResponse @JvmOverloads constructor(
    @field: Attribute
    var pubdate: String? = null,
    @field: Attribute
    var totalitems: String? = null,
    @field: ElementList(inline = true)
    var boardGamesList: List<BoardGameResponse>? = null
)

//Example pubdate -> "Wed, 13 Jan 2021 12:23:22 +0000"