package es.kiketurry.talkingabout.data.repository.remote.mapper.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.*
import es.kiketurry.talkingabout.data.repository.remote.mapper.ResponseMapper
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ThingBoardGameGeekResponse
import java.util.*
import kotlin.collections.ArrayList

class ThingBGGMapper : ResponseMapper<ThingBoardGameGeekResponse, ThingBGGModel> {

    override fun fromResponse(response: ThingBoardGameGeekResponse): ThingBGGModel {

        val type: ThingBGGModel.TypeThingBGG = when (response.type) {
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

        val languageDependenceType: ThingBGGModel.LanguageDependenceThingBGG =
            if (languageDependence.contains("No necessary", true)) {
                LANGUAGE_DEPENDENCE_THING_NO_NECESARY
            } else if (languageDependence.contains("Some necessary", true)) {
                LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY
            } else if (languageDependence.contains("Moderate", true)) {
                LANGUAGE_DEPENDENCE_THING_MODERATE
            } else if (languageDependence.contains("Extensive", true)) {
                LANGUAGE_DEPENDENCE_THING_EXTENSIVE
            } else if (languageDependence.contains("Unplayable", true)) {
                LANGUAGE_DEPENDENCE_THING_UNPLAYABLE
            } else {
                LANGUAGE_DEPENDENCE_THING_UNKNOW
            }

        val playTime =
            if (response.maxplaytime.value != null && response.minplaytime.value != null && response.maxplaytime.value.equals(response.minplaytime.value)) {
                response.maxplaytime.value!!
            } else {
                "${response.maxplaytime.value!!}-${response.minplaytime.value!!}'"
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

        //TODO kiketurry ver como coger el idioma español

        return ThingBGGModel(
            response.id.toInt(),
            type,
            response.image,
            namePrimary,
            nameEs,
            listNames,
            response.description,
            response.yearpublished.value ?: "-",
            "${response.minplayers.value} - ${response.maxplayers.value}",
            playersRecommendedCommunity,
            response.minage.value ?: "-",
            ageRecommendedCommunity,
            playTime,
            response.statistics.ratings.averageweight.value?.toDouble() ?: 0.0,
            languageDependenceType,
            rank,
            Date().time,
        )
    }
}