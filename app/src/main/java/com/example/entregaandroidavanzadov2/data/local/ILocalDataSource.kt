package com.example.entregaandroidavanzadov2.data.local

import com.example.entregaandroidavanzadov2.data.local.model.LocalHero

interface ILocalDataSource {

    suspend fun getHeros(): List<LocalHero>

    suspend fun getHero(heroID: String): LocalHero

    suspend fun updateHero(heroID: String, favorite: Boolean)

    suspend fun insertHero(localHero: LocalHero)

    suspend fun insertHeros(localHero: List<LocalHero>)
}