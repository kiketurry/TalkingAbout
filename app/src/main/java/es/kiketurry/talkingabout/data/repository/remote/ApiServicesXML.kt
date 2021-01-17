package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.listuser.ListBoardGameUserResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.bgg.things.ListThingsBoardGameGeekResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicesXML {

    //API LIST
    //############
    @GET("collection")
    fun getListBoardGameUser(
        @Query("username") username: String,
        @Query("own") own: Int,
        @Query("excludesubtype") excludesubtype: String,
    ): Call<ListBoardGameUserResponse>


    @GET("thing")
    fun getThingBoardGameGeek(
        @Query("id") id: String,
        @Query("stats") stats: Int,
    ): Call<ListThingsBoardGameGeekResponse>

}