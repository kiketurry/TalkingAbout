package es.kiketurry.talkingabout.data.repository.remote.mapper.bgg

import android.util.Log
import es.kiketurry.talkingabout.data.domain.model.bgg.ListUserBGGModel
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ListUserBGGMapper : ResponseMapper<ListBoardGameUserResponse, ListUserBGGModel> {
    private val TAG: String? = ListUserBGGMapper::class.qualifiedName

    override fun fromResponse(response: ListBoardGameUserResponse): ListUserBGGModel {

        val listThings: ArrayList<Int> = ArrayList()
        response.boardGamesList?.forEachIndexed { _, boardGameResponse ->
            if (!boardGameResponse.objectid.isNullOrBlank() && boardGameResponse.status.own.equals("1")) {
                listThings.add(boardGameResponse.objectid!!.toInt())
            }
        }

        var longDate = 0L
        try {
            val simpleDateFormat = SimpleDateFormat("EE, d MMM y H:m:s Z", Locale.ENGLISH)
            val date: Date = simpleDateFormat.parse(response.pubdate)
            longDate = date.time
        } catch (exception: Exception) {
            Log.i(TAG, "l> problemas parseando fecha de la lista de juegos: ${exception.message}")
        }

        return ListUserBGGModel(
            "",
            listThings.size,
            0,
            0,
            listThings,
            longDate
        )
    }
}

//Example pubdate -> "Wed, 13 Jan 2021 12:23:22 +0000"