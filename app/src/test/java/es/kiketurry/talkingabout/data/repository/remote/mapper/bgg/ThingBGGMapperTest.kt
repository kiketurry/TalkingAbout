package es.kiketurry.talkingabout.data.repository.remote.mapper.bgg

import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.LanguageDependenceThingBGG.*
import es.kiketurry.talkingabout.data.domain.model.bgg.ThingBGGModel.TypeThingBGG.*
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.DataValueResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.NameBoardGameGeekResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ThingBoardGameGeekResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls.PollResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls.PollResultDetailResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.polls.PollResultResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics.RankDataResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics.RankResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics.RatingResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.statistics.StatisticsResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ThingBGGMapperTest {

    lateinit var realThingBoardGameGeekResponse: ThingBoardGameGeekResponse

    @Before
    fun setup() {
        val nameOne = NameBoardGameGeekResponse("primary", "Ethnos")
        val nameTwo = NameBoardGameGeekResponse("alternate", "Этнос")
        val nameThree = NameBoardGameGeekResponse("alternate", "エスノス")
        val nameFour = NameBoardGameGeekResponse("alternate", "에스노스")
        val listNames: List<NameBoardGameGeekResponse> = arrayListOf(nameOne, nameTwo, nameThree, nameFour)

        val numPlayer1PollResultDetailOne = PollResultDetailResponse("Best", "0", "")
        val numPlayer1PollResultDetailTwo = PollResultDetailResponse("Recommended", "0", "")
        val numPlayer1PollResultDetailThree = PollResultDetailResponse("Not Recommended", "81", "")
        val pollResult1Players: PollResultResponse =
            PollResultResponse("1", arrayListOf(numPlayer1PollResultDetailOne, numPlayer1PollResultDetailTwo, numPlayer1PollResultDetailThree))
        val numPlayer2PollResultDetailOne = PollResultDetailResponse("Best", "5", "")
        val numPlayer2PollResultDetailTwo = PollResultDetailResponse("Recommended", "54", "")
        val numPlayer2PollResultDetailThree = PollResultDetailResponse("Not Recommended", "47", "")
        val pollResult2Players =
            PollResultResponse("1", arrayListOf(numPlayer2PollResultDetailOne, numPlayer2PollResultDetailTwo, numPlayer2PollResultDetailThree))
        val numPlayer3PollResultDetailOne = PollResultDetailResponse("Best", "14", "")
        val numPlayer3PollResultDetailTwo = PollResultDetailResponse("Recommended", "79", "")
        val numPlayer3PollResultDetailThree = PollResultDetailResponse("Not Recommended", "14", "")
        val pollResult3Players =
            PollResultResponse("1", arrayListOf(numPlayer3PollResultDetailOne, numPlayer3PollResultDetailTwo, numPlayer3PollResultDetailThree))
        val numPlayer4PollResultDetailOne = PollResultDetailResponse("Best", "84", "")
        val numPlayer4PollResultDetailTwo = PollResultDetailResponse("Recommended", "31", "")
        val numPlayer4PollResultDetailThree = PollResultDetailResponse("Not Recommended", "1", "")
        val pollResult4Players =
            PollResultResponse("1", arrayListOf(numPlayer4PollResultDetailOne, numPlayer4PollResultDetailTwo, numPlayer4PollResultDetailThree))
        val numPlayer5PollResultDetailOne = PollResultDetailResponse("Best", "47", "")
        val numPlayer5PollResultDetailTwo = PollResultDetailResponse("Recommended", "48", "")
        val numPlayer5PollResultDetailThree = PollResultDetailResponse("Not Recommended", "5", "")
        val pollResult5Players: PollResultResponse =
            PollResultResponse("1", arrayListOf(numPlayer5PollResultDetailOne, numPlayer5PollResultDetailTwo, numPlayer5PollResultDetailThree))
        val numPlayer6PollResultDetailOne = PollResultDetailResponse("Best", "15", "")
        val numPlayer6PollResultDetailTwo = PollResultDetailResponse("Recommended", "65", "")
        val numPlayer6PollResultDetailThree = PollResultDetailResponse("Not Recommended", "15", "")
        val pollResult6Players =
            PollResultResponse("1", arrayListOf(numPlayer6PollResultDetailOne, numPlayer6PollResultDetailTwo, numPlayer6PollResultDetailThree))
        val listResultsNumberPlayer: List<PollResultResponse> =
            arrayListOf(pollResult1Players, pollResult2Players, pollResult3Players, pollResult4Players, pollResult5Players, pollResult6Players)
        val pollNumberPlayers = PollResponse("suggested_numplayers", "User Suggested Number of Players", "149", listResultsNumberPlayer)

        val playerAgePollResultDetailOne = PollResultDetailResponse("2", "0", "")
        val playerAgePollResultDetailTwo = PollResultDetailResponse("3", "0", "")
        val playerAgePollResultDetailThree = PollResultDetailResponse("4", "0", "")
        val playerAgePollResultDetailFour = PollResultDetailResponse("5", "0", "")
        val playerAgePollResultDetailFive = PollResultDetailResponse("6", "1", "")
        val playerAgePollResultDetailSix = PollResultDetailResponse("8", "7", "")
        val playerAgePollResultDetailSeven = PollResultDetailResponse("10", "8", "")
        val playerAgePollResultDetailEight = PollResultDetailResponse("12", "4", "")
        val playerAgePollResultDetailNive = PollResultDetailResponse("14", "0", "")
        val playerAgePollResultDetailTen = PollResultDetailResponse("16", "0", "")
        val playerAgePollResultDetailEleven = PollResultDetailResponse("18", "0", "")
        val playerAgePollResultDetailTwelve = PollResultDetailResponse("21 and up", "0", "")
        val pollResultPlayerAge = PollResultResponse(
            pollResultDetailResponseList = arrayListOf(
                playerAgePollResultDetailOne,
                playerAgePollResultDetailTwo,
                playerAgePollResultDetailThree,
                playerAgePollResultDetailFour,
                playerAgePollResultDetailFive,
                playerAgePollResultDetailSix,
                playerAgePollResultDetailSeven,
                playerAgePollResultDetailEight,
                playerAgePollResultDetailNive,
                playerAgePollResultDetailTen,
                playerAgePollResultDetailEleven,
                playerAgePollResultDetailTwelve
            )
        )
        val listResultsAgePlayer: List<PollResultResponse> = arrayListOf(pollResultPlayerAge)
        val pollAgePlayers = PollResponse("suggested_playerage", "User Suggested Player Age", "20", listResultsAgePlayer)

        val languageDependencePollResultDetailOne = PollResultDetailResponse("No necessary in-game text", "0", "1096")
        val languageDependencePollResultDetailTwo =
            PollResultDetailResponse("Some necessary text - easily memorized or small crib sheet", "7", "1097")
        val languageDependencePollResultDetailThree =
            PollResultDetailResponse("Moderate in-game text - needs crib sheet or paste ups", "3", "1098")
        val languageDependencePollResultDetailFour =
            PollResultDetailResponse("Extensive use of text - massive conversion needed to be playable", "0", "1099")
        val languageDependencePollResultDetailFive = PollResultDetailResponse("Unplayable in another language", "0", "1100")
        val pollResultLanguageDependence = PollResultResponse(
            pollResultDetailResponseList = arrayListOf(
                languageDependencePollResultDetailOne,
                languageDependencePollResultDetailTwo,
                languageDependencePollResultDetailThree,
                languageDependencePollResultDetailFour,
                languageDependencePollResultDetailFive
            )
        )
        val listResultsLanguageDependence: List<PollResultResponse> = arrayListOf(pollResultLanguageDependence)
        val pollLanguageDependence = PollResponse("language_dependence", "Language Dependence", "10", listResultsLanguageDependence)

        val listPolls: List<PollResponse> = arrayListOf(pollNumberPlayers, pollAgePlayers, pollLanguageDependence)

        val rankResponse = RankResponse(arrayListOf(RankDataResponse("Board Game Rank", "247"), RankDataResponse("Strategy Game Rank", "169")))
        val ratingResponse = RatingResponse(rankResponse, DataValueResponse("2.0351"))
        val statisticsData = StatisticsResponse(ratingResponse)

        realThingBoardGameGeekResponse = ThingBoardGameGeekResponse(
            type = "boardgame",
            id = "206718",
            thumbnail = "https://cf.geekdo-images.com/UIKUXkcnH3Bd4fjcPGINcA__thumb/img/oNhw2e_wcBc37ItkTRZVio2gMGQ=/fit-in/200x150/filters:strip_icc()/pic3304124.png",
            image = "https://cf.geekdo-images.com/UIKUXkcnH3Bd4fjcPGINcA__original/img/IrqDqjjm1LY66Zkw7l4RwppaBBI=/0x0/filters:format(png)/pic3304124.png",
            namesList = listNames,
            description = "In Ethnos, players call upon the support of giants, merfolk, halflings, minotaurs, and other fantasy tribes to help them gain control of the land. After three ages of play, whoever has collected the most glory wins!&amp;#10;&amp;#10;In more detail, the land of Ethnos contains twelve tribes of fantasy creatures, and in each game you choose six of them (five in a 2/3-player game), then create a deck with only the creatures in those tribes. The cards come in six colors, which match the six regions of Ethnos. Place three glory tokens in each region at random, arranging them from low to high.&amp;#10;&amp;#10;Each player starts the game with one card in hand, then 4-12 cards (double the number of players) are placed face up on the table. On a turn, a player either recruits an ally or plays a band of allies. In the former case, you take a face-up card (without replacing it from the deck) or the top card of the deck and add it to your hand. In the latter case, you choose a set of cards in your hand that match either in tribe or in color, play them in front of you on the table, then discard all other cards in hand. You then place one token in the region that matches the color of the top card just played, and you use the power of the tribe member on the top card just played.&amp;#10;&amp;#10;At the end of the first age, whoever has the most tokens in a region scores the glory shown on the first token. After the second age, the players with the most and second most tokens score glory equal to the values shown on the first and second tokens respectively. Players score similarly after the third age, then whoever has the most glory wins. (Games with two and three players last only two ages.)&amp;#10;&amp;#10;",
            yearpublished = DataValueResponse("2017"),
            minplayers = DataValueResponse("2"),
            maxplayers = DataValueResponse("6"),
            playingtime = DataValueResponse("60"),
            minplaytime = DataValueResponse("45"),
            maxplaytime = DataValueResponse("60"),
            minage = DataValueResponse("14"),
            pollList = listPolls,
            statistics = statisticsData
        )
    }

    @Test
    fun thingBGGMapperTypeTest() {
        val thingBGGMapper = ThingBGGMapper()
        assertEquals(TYPE_THING_BOARDGAME, thingBGGMapper.typeThingBGG("boardgame"))
        assertEquals(TYPE_THING_EXPANSION, thingBGGMapper.typeThingBGG("boardgameexpansion"))
        assertEquals(TYPE_THING_UNKNOW, thingBGGMapper.typeThingBGG("añlksdhjfñalksjd"))
    }

    @Test
    fun thingBGGMapperLanguageDependenceTest() {
        val thingBGGMapper = ThingBGGMapper()
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_NO_NECESARY,
            thingBGGMapper.languageDependenceThingBGG("No necessary in-game text")
        )
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY,
            thingBGGMapper.languageDependenceThingBGG("Some necessary text - easily memorized or small crib sheet")
        )
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_MODERATE,
            thingBGGMapper.languageDependenceThingBGG("Moderate in-game text - needs crib sheet or paste ups")
        )
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_EXTENSIVE,
            thingBGGMapper.languageDependenceThingBGG("Extensive use of text - massive conversion needed to be playable")
        )
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_UNPLAYABLE,
            thingBGGMapper.languageDependenceThingBGG("Unplayable in another language")
        )
        assertEquals(
            LANGUAGE_DEPENDENCE_THING_UNKNOW,
            thingBGGMapper.languageDependenceThingBGG("añlksdhjfñalksjd")
        )
    }

    @Test
    fun thingBGGResponseToThingBGGModelMapperTest() {
        val thingBGGModel = ThingBGGMapper().fromResponse(realThingBoardGameGeekResponse)
        assertEquals(TYPE_THING_BOARDGAME, thingBGGModel.type)
        assertEquals("Ethnos", thingBGGModel.nameFirst)
        assertEquals("2 - 6", thingBGGModel.playersNumber)
        assertEquals("1", thingBGGModel.playersRecommendedCommunity)
        assertEquals("10", thingBGGModel.ageMinRecommendedCommunity)
        assertEquals("45-60'", thingBGGModel.time)
        assertEquals("2,04", thingBGGModel.weight)
        assertEquals(LANGUAGE_DEPENDENCE_THING_SOME_NECESSARY, thingBGGModel.languageDependence)
        assertEquals("247", thingBGGModel.rank)
    }

}