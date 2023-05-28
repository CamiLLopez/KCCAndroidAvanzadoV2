package com.example.entregaandroidavanzadov2.data.remote

import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHerosResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getHeros(): List<GetHerosResponse>

    suspend fun login(credentials: String): Response<String>

    suspend fun favouriteHero()

    suspend fun getHeroLocations(): List<GetHeroLocationResponse>
}