package es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class StatusBoardGameResponse @JvmOverloads constructor(
    @field: Attribute
    var own: String? = null,
    @field: Attribute
    var prevowned: String? = null,
    @field: Attribute
    var fortrade: String? = null,
    @field: Attribute
    var want: String? = null,
    @field: Attribute
    var wanttoplay: String? = null,
    @field: Attribute
    var wanttobuy: String? = null,
    @field: Attribute
    var wishlist: String? = null,
    @field: Attribute
    var preordered: String? = null,
    @field: Attribute
    var lastmodified: String? = null,
)

//Example lastmodified -> "2018-04-24 09:08:33"