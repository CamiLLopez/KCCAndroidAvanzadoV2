package com.example.entregaandroidavanzadov2.data.local

import com.example.entregaandroidavanzadov2.data.local.model.LocalHero

class LocalDataSourceImpl: ILocalDataSource {


    override suspend fun getHeros(): List<LocalHero> {
        TODO("Not yet implemented")
    }

    override suspend fun insertHero(localHero: LocalHero) {
        TODO("Not yet implemented")
    }

    override suspend fun insertHeros(localHero: List<LocalHero>) {
        TODO("Not yet implemented")
    }
}