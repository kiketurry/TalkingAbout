package es.kiketurry.talkingabout.data.repository.remote.mapper.bgg

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.*
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ThingBoardGameGeekResponse
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

class ThingBGGMapper : ResponseMapper<ThingBoardGameGeekResponse, ThingBGGModel> {

    override fun fromResponse(response: ThingBoardGameGeekResponse): ThingBGGModel {

        val type: ThingBGGModel.TypeThingBGG = typeThingBGG(response.type)

        val listNames: ArrayList<String> = ArrayList()
        var namePrimary = ""
        var nameEs = ""
        response.namesList?.forEachIndexed { _, nameBoardGameGeekResponse ->
            nameBoardGameGeekResponse.nameThing?.let {
                listNames.add(it)
                if (nameBoardGameGeekResponse.type == "primary") {
                    namePrimary = it
                }
            }
        }

        var numPlayerResult = "0"
        var playersRecommendedCommunity = "-"
        var playersRecommendedCommunityMaxVotes = 0

        var ageRecommendedCommunity = "-"
        var ageRecommendedCommunityMaxVotes = 0

        var languageDependence = "-"
        var languageDependenceVotes = 0

        response.pollList?.forEachIndexed { _, pollResponse ->
            if (pollResponse.namePoll.equals("suggested_numplayers")) {
                pollResponse.results?.forEachIndexed { _, pollResultResponse ->
                    numPlayerResult = pollResultResponse.numplayers.toString()
                    pollResultResponse.pollResultDetailResponseList?.forEachIndexed { _, pollResultDetailResponse ->
                        if (pollResultDetailResponse.dataValue != null
                            && pollResultDetailResponse.dataValue.equals("Best")
                            && pollResultDetailResponse.numvotes != null
                            && pollResultDetailResponse.numvotes?.toInt()!! > playersRecommendedCommunityMaxVotes
                        ) {
                            playersRecommendedCommunityMaxVotes = pollResultDetailResponse.numvotes!!.toInt()
                            playersRecommendedCommunity = numPlayerResult
                        }
                    }
                }
            }

            if (pollResponse.namePoll.equals("suggested_playerage")) {
                pollResponse.results?.forEachIndexed { _, pollResultResponse ->
                    pollResultResponse.pollResultDetailResponseList?.forEachIndexed { _, pollResultDetailResponse ->
                        if (pollResultDetailResponse.dataValue != null
                            && pollResultDetailResponse.numvotes != null
                            && pollResultDetailResponse.numvotes?.toInt()!! > ageRecommendedCommunityMaxVotes
                        ) {
                            ageRecommendedCommunityMaxVotes = pollResultDetailResponse.numvotes!!.toInt()
                            ageRecommendedCommunity = pollResultDetailResponse.dataValue!!
                        }
                    }
                }
            }

            if (pollResponse.namePoll.equals("language_dependence")) {
                pollResponse.results?.forEachIndexed { _, pollResultResponse ->
                    pollResultResponse.pollResultDetailResponseList?.forEachIndexed { _, pollResultDetailResponse ->
                        if (pollResultDetailResponse.dataValue != null
                            && pollResultDetailResponse.numvotes != null
                            && pollResultDetailResponse.numvotes?.toInt()!! > languageDependenceVotes
                        ) {
                            languageDependenceVotes = pollResultDetailResponse.numvotes!!.toInt()
                            languageDependence = pollResultDetailResponse.dataValue!!
                        }
                    }
                }
            }
        }

        val languageDependenceType: ThingBGGModel.LanguageDependenceThingBGG = languageDependenceThingBGG(languageDependence)

        val playTime =
            if (response.maxplaytime.value != null && response.minplaytime.value != null && response.maxplaytime.value.equals(response.minplaytime.value)) {
                response.maxplaytime.value!!
            } else {
                "${response.minplaytime.value!!}-${response.maxplaytime.value!!}'"
            }

        var rank = "-"
        response.statistics.ratings.ranks.rankList?.forEachIndexed { _, rankDataResponse ->
            if (rankDataResponse.friendlyname.equals("Board Game Rank")) {
                rank = rankDataResponse.valueData.toString()
                if (rank.equals("Not Ranked")) {
                    rank = "-"
                }
            }
        }

        var weight = "-"
        var weightDouble = response.statistics.ratings.averageweight.value?.toDoubleOrNull()
        if (weightDouble != null) {
            val decimalFormat = DecimalFormat("#.##")
            decimalFormat.roundingMode = RoundingMode.HALF_EVEN
            weight = decimalFormat.format(weightDouble).toString()
        }

        var description: String = response.description
        var descriptionHtml: String? = null
        if (description.isNotBlank()) {
            descriptionHtml = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                val fromHtml: Spanned? = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                fromHtml.toString() ?: ""
            }
        }

        if (descriptionHtml != null && !descriptionHtml.equals("null", true) && descriptionHtml.isNotBlank()) {
            description = descriptionHtml
        }

        return ThingBGGModel(
            response.id.toInt(),
            type,
            response.image,
            namePrimary,
            nameEs,
            listNames,
            description,
            response.yearpublished.value ?: "-",
            "${response.minplayers.value} - ${response.maxplayers.value}",
            playersRecommendedCommunity,
            response.minage.value ?: "-",
            ageRecommendedCommunity,
            playTime,
            weight,
            languageDependenceType,
            rank,
            Date().time,
        )
    }

    fun typeThingBGG(type: String) = when (type) {
        "boardgame" -> {
            ThingBGGModel.TypeThingBGG.TYPE_THING_BOARDGAME
        }
        "boardgameexpansion" -> {
            ThingBGGModel.TypeThingBGG.TYPE_THING_EXPANSION
        }
        else -> {
            ThingBGGModel.TypeThingBGG.TYPE_THING_UNKNOW
        }
    }

    fun languageDependenceThingBGG(languageDependence: String) = when {
        languageDependence.contains("No necessary", true) -> {
            LANGUAGE_DEPENDENCE_THING_NO_NECESARY
        }
        languageDependence.contains("Some necessary", true) -> {
            LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY
        }
        languageDependence.contains("Moderate", true) -> {
            LANGUAGE_DEPENDENCE_THING_MODERATE
        }
        languageDependence.contains("Extensive", true) -> {
            LANGUAGE_DEPENDENCE_THING_EXTENSIVE
        }
        languageDependence.contains("Unplayable", true) -> {
            LANGUAGE_DEPENDENCE_THING_UNPLAYABLE
        }
        else -> {
            LANGUAGE_DEPENDENCE_THING_UNKNOW
        }
    }
}