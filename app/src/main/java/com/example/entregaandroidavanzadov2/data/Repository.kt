package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.SuperHero


interface Repository {

    suspend fun getHeros(): List<SuperHero>

}