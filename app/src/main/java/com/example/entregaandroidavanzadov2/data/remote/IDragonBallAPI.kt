package com.example.entregaandroidavanzadov2.data.remote


import com.example.entregaandroidavanzadov2.data.remote.Request.FavouriteHeroRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Request.GetHeroLocationRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Request.GetHerosRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHerosResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface IDragonBallAPI {

    @POST("api/heros/all")
    suspend fun getHeroes(@Header("Authorization") token: String, @Body getHerosRequestBody: GetHerosRequestBody): List<GetHerosResponse>


    @POST("api/data/herolike")
    suspend fun favouriteHero(@Header("Authorization") token: String,@Body favouriteHeroRequestBody: FavouriteHeroRequestBody)

    @POST("api/heros/locations")
    suspend fun getHeroLocations(@Header("Authorization") token: String, @Body getHeroLocationRequestBody: GetHeroLocationRequestBody): List<GetHeroLocationResponse>
}