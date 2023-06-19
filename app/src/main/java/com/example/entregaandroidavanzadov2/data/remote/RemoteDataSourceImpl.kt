package com.example.entregaandroidavanzadov2.data.remote
import com.example.entregaandroidavanzadov2.data.remote.request.FavouriteHeroRequestBody
import com.example.entregaandroidavanzadov2.data.remote.request.GetHeroLocationRequestBody
import com.example.entregaandroidavanzadov2.data.remote.request.GetHerosRequestBody
import com.example.entregaandroidavanzadov2.data.remote.response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.response.GetHerosResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val api: IDragonBallAPI,
    private val loginAPI: ILoginAPI
    ): RemoteDataSource {

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