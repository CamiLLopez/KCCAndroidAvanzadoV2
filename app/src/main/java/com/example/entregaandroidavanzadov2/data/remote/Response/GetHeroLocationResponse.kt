package com.example.entregaandroidavanzadov2.data.remote.Response

import com.squareup.moshi.Json

data class GetHeroLocationResponse (

    @Json(name = "longitud") val longitud : Double,
    @Json(name = "latitud") val latitud : Double
    )
