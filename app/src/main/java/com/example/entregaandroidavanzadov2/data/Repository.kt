package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse


interface Repository {

    suspend fun getHeros(): List<SuperHero>

    suspend fun getLocations(heroID: String): List<GetHeroLocationResponse>

}