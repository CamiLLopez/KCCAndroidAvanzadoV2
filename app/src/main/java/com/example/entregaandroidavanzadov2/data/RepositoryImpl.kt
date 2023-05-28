package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.MappingClasses.LocalToSuperHeroMapper
import com.example.entregaandroidavanzadov2.data.MappingClasses.RemoteToLocalMapper
import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource

class RepositoryImpl(

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
}