package com.example.entregaandroidavanzadov2.data

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.MappingClasses.LocalToSuperHeroMapper
import com.example.entregaandroidavanzadov2.data.MappingClasses.RemoteLocationsToLocationHero
import com.example.entregaandroidavanzadov2.data.MappingClasses.RemoteToLocalMapper
import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(

    private val localDataSource: ILocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val  remoteToLocalMapper: RemoteToLocalMapper,
    private val localToSuperHeroMapper: LocalToSuperHeroMapper,
    private val remoteLocationsToLocationHero: RemoteLocationsToLocationHero

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

    override suspend fun getLocations(heroID: String): List<LocationsHero>? {
        val response = remoteDataSource.getHeroLocations(heroID)

        val locationsToShow = remoteLocationsToLocationHero.mapRemoteToLocationsHero(response)

        if(!locationsToShow.isEmpty()){

            return locationsToShow

        }else{
            return null
        }
    }

    override suspend fun markFavoriteHero(heroID: String, favorite: Boolean) {

        remoteDataSource.favouriteHero(heroID)
        localDataSource.updateHero(heroID, favorite)

    }

}