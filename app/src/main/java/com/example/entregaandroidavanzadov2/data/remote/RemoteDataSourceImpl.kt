package com.example.entregaandroidavanzadov2.data.remote
import com.example.entregaandroidavanzadov2.data.remote.Request.FavouriteHeroRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Request.GetHeroLocationRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Request.GetHerosRequestBody
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHerosResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(private val api: IDragonBallAPI, private val loginAPI: ILoginAPI): RemoteDataSource {

    private lateinit var heroID: String
    override lateinit var token: String

    override suspend fun getHeros(): List<GetHerosResponse> {
        return api.getHeroes("Bearer $token", GetHerosRequestBody())
    }

    override suspend fun login(credentials: String): Response<String> {
        return loginAPI.login(credentials)
    }

    override suspend fun favouriteHero(heroID: String) {
        return api.favouriteHero("Bearer $token", FavouriteHeroRequestBody(heroID))
    }

    override suspend fun getHeroLocations(heroID: String): List<GetHeroLocationResponse> {
        return api.getHeroLocations("Bearer $token", GetHeroLocationRequestBody(heroID))
    }
}