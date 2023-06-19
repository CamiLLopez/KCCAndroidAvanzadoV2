package com.example.entregaandroidavanzadov2.data.mappingClasses

import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import javax.inject.Inject

class LocalToSuperHeroMapper @Inject constructor() {
    fun mapLocalToSuperHeros(localHeros: List<LocalHero>): List<SuperHero>{
        return localHeros.map { mapLocalToSuperHero(it) }
    }
     fun mapLocalToSuperHero(localHero: LocalHero) : SuperHero {
        return SuperHero(localHero.id, localHero.name, localHero.favorite, localHero.description, localHero.photo)
    }
}