package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.MappingClasses.LocalToSuperHeroMapper
import com.example.entregaandroidavanzadov2.data.MappingClasses.RemoteToLocalMapper
import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(

    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val  remoteToLocalMapper: RemoteToLocalMapper,
    private val localToSuperHeroMapper: LocalToSuperHeroMapper

): Repository {

    override suspend fun getHeros(): List<SuperHero> {
        if (localDataSource.getHeros().isEmpty()){
            val remoteHeros = remoteDataSource.getHeros()
            localDataSource.insertHeros(remoteToLocalMapper.mapGetHerosResponse(remoteHeros))
        }
        return localToSuperHeroMapper.mapLocalToSuperHeros(localDataSource.getHeros())
    }

    override suspend fun getHero(heroID: String): SuperHero {
        val localHero = localDataSource.getHero(heroID)
        return localToSuperHeroMapper.mapLocalToSuperHero(localHero)
    }

    override suspend fun getLocations(heroID: String): List<GetHeroLocationResponse>? {
        val response = remoteDataSource.getHeroLocations(heroID)

        if(!response.isEmpty()){

            return response

        }else{
            return null
        }
    }

}