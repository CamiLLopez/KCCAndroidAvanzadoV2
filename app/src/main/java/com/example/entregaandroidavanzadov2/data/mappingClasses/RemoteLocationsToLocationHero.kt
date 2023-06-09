package com.example.entregaandroidavanzadov2.data.mappingClasses

import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.data.remote.response.GetHeroLocationResponse
import javax.inject.Inject

class RemoteLocationsToLocationHero @Inject constructor(){
        fun mapRemoteToLocationsHero(remoteHeros: List<GetHeroLocationResponse>): List<LocationsHero>{
            return remoteHeros.map { mapRemoteToLocationHero(it) }
        }
       private fun mapRemoteToLocationHero(remoteHero: GetHeroLocationResponse) : LocationsHero {
            return LocationsHero(remoteHero.latitud, remoteHero.longitud)
       }
}