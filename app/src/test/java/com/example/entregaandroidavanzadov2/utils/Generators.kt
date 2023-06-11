package com.example.entregaandroidavanzadov2.utils

import com.example.entregaandroidavanzadov2.LocationsHero
import com.example.entregaandroidavanzadov2.SuperHero
import com.example.entregaandroidavanzadov2.data.local.model.LocalHero
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHeroLocationResponse
import com.example.entregaandroidavanzadov2.data.remote.Response.GetHerosResponse
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

fun generateHerosList(length: Int): List<SuperHero> {

    return (0 until length).map{ SuperHero("ID $it", "Name $it", false, "Description: somethin", "Photo: sin foto") }

}

fun generateLocalHeroList(length: Int): List<LocalHero> {

    return (0 until length).map{ LocalHero("ID $it", "Name $it", false, "Description: somethin", "Photo: sin foto") }

}

fun generateHerosResponse(length: Int): List<GetHerosResponse> {

    return (0 until  length).map { GetHerosResponse("ID $it", "Name $it", "Description: somethin",false, "Photo: sin foto") }

}
fun generateLocalHero(): LocalHero {

    return  LocalHero("ID 1234", "Example", false, "Description: something", "Photo: sin foto")

}

fun generateSuperHero(): SuperHero{
    return  SuperHero("963CA612-716B-4D08-991E-8B1AFF625A81","Androide 17",
    false, "Es el hermano gemelo de Androide 18. Son muy parecidos físicamente, aunque Androide 17 es un joven moreno. También está programado para destruir a Goku porque fue el responsable de exterminar el Ejército Red Ribbon. Sin embargo, mató a su creador el Dr. Gero por haberle convertido en un androide en contra de su voluntad. Es un personaje con mucha confianza en sí mismo, sarcástico y rebelde que no se deja pisotear. Ese exceso de confianza le hace cometer errores que pueden costarle la vida",
     "https://cdn.alfabetajuega.com/alfabetajuega/2019/10/dragon-ball-androide-17.jpg?width=300")

}

fun generateHeroLocationsResponse(length: Int): List<GetHeroLocationResponse>{

    return (0 until length).map { GetHeroLocationResponse(48.8589465, 2.2768237) }

}

fun generateHeroLocationsHero(length: Int): List<LocationsHero>{

    return (0 until length).map { LocationsHero(48.8589465, 2.2768237) }

}
fun generateLoginToken(): String {

    return "eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjFFQzk5RDAzLTRDMTMtNEZFNS1CMERBLUJCQjQxRkFFMzdERiIsImVtYWlsIjoiY2FtaWxhbGxvcGV6OTVAZ21haWwuY29tIiwiZXhwaXJhdGlvbiI6NjQwOTIyMTEyMDB9.mCt5VQOCzuB4cdv8sm_RDmEGoINTeS3tCZlZ2l2pLYY"
}

fun generateLoginResponse(errorResponse: String): Response<String> {

    return if(errorResponse=="badCredentials"){
        Response.error(400, byteArrayOf().toResponseBody())
    }else{
        Response.success(200, generateLoginToken())
    }
}