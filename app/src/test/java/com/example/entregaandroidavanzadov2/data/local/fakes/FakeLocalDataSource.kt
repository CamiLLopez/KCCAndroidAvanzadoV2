package com.example.entregaandroidavanzadov2.data.local.fakes

import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import com.example.entregaandroidavanzadov2.utils.generateLocalHero
import com.example.entregaandroidavanzadov2.utils.generateLocalHeroList

class FakeLocalDataSource: ILocalDataSource {

    private var firstTime = true

    override suspend fun getHeros(): List<LocalHero> {

        if (firstTime){
            firstTime = false
            return emptyList()
        }else{
            return  generateLocalHeroList(16)
        }
    }

    override suspend fun getHero(heroID: String): LocalHero {

        return generateLocalHero()
    }

    override suspend fun updateHero(heroID: String, favorite: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun insertHero(localHero: LocalHero) {
        TODO("Not yet implemented")
    }

    override suspend fun insertHeros(localHero: List<LocalHero>) {

    }


}