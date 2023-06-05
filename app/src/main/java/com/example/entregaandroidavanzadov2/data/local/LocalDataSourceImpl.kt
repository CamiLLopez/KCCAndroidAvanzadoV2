package com.example.entregaandroidavanzadov2.data.local

import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: IHeroDAO): ILocalDataSource {

    override suspend fun getHeros(): List<LocalHero>{
        return dao.getAll()
    }

    override suspend fun getHero(heroID: String): LocalHero {
        return dao.getHeroByID(heroID)
    }

    override suspend fun updateHero(heroID: String, favorite: Boolean) {
        return dao.updateHero(heroID, favorite)
    }

    override suspend fun insertHero(localHero: LocalHero){
        dao.insertAllList(listOf(localHero))
    }

    override suspend fun insertHeros(localHero: List<LocalHero>){
        dao.insertAllList(localHero)
    }
}