package com.example.entregaandroidavanzadov2.data.mappingClasses

import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import com.example.entregaandroidavanzadov2.data.remote.response.GetHerosResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {
    fun mapGetHerosResponse(getHerosResponse: List<GetHerosResponse>): List<LocalHero>{
        return getHerosResponse.map { mapGetHeroResponse(it) }
    }
    private fun mapGetHeroResponse(getHeroResponse: GetHerosResponse) : LocalHero {
        return LocalHero(getHeroResponse.id, getHeroResponse.name,getHeroResponse.favorite ,
            getHeroResponse.photo, getHeroResponse.description)
    }
}