package com.example.entregaandroidavanzadov2.data.remote.fakes

import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.response.GetHerosResponse
import com.example.entregaandroidavanzadov2.utils.generateHerosResponse
import com.example.entregaandroidavanzadov2.utils.generateLoginResponse
import retrofit2.Response

class FakeRemoteDataSource: RemoteDataSource {
    override var token: String
        get() = TODO("Not yet implemented")
        set(value) {}

    override suspend fun getHeros(): List<GetHerosResponse> {
        return generateHerosResponse(16)
    }

    override suspend fun login(credentials: String): Response<String> {
        return generateLoginResponse(credentials)
    }

    override suspend fun favouriteHero(heroID: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getHeroLocations(heroID: String): List<GetHeroLocationResponse> {
        TODO("Not yet implemented")
    }
}