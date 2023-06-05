package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse


interface Repository {

    suspend fun getHeros(): List<SuperHero>

    suspend fun getHero(heroID: String): SuperHero

    suspend fun getLocations(heroID: String): List<LocationsHero>?
    suspend fun markFavoriteHero(heroID: String, favorite: Boolean)

}