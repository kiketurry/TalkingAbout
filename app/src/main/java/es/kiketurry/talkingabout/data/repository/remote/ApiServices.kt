package es.kiketurry.talkingabout.data.repository.remote

import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedPhotoResponse
import es.kiketurry.talkingabout.data.repository.remote.responses.breeds.BreedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    //API LIST
    //############
    @GET("v1/breeds")
    fun getBreeds(
        @Query("limit") quantity: Int
    ): Call<ArrayList<BreedResponse>>

    @GET("v1/images/search")
    fun getImages(
        @Query("breed_id") breedId: String,
        @Query("order") order: String,
        @Query("limit") quantity: Int,
    ): Call<ArrayList<BreedPhotoResponse>>

}