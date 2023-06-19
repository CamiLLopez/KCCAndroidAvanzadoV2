package com.example.entregaandroidavanzadov2.data.remote

import com.example.entregaandroidavanzadov2.data.remote.response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.response.GetHerosResponse
import retrofit2.Response

interface RemoteDataSource {

    var token: String
    suspend fun getHeros(): List<GetHerosResponse>
    suspend fun login(credentials: String): Response<String>
    suspend fun favouriteHero(heroID: String)
    suspend fun getHeroLocations(heroID: String): List<GetHeroLocationResponse>
}