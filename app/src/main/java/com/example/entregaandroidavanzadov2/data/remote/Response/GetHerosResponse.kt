package com.example.entregaandroidavanzadov2.data.remote.Response


import com.squareup.moshi.Json

data class GetHerosResponse(


    @Json(name = "id") val id: String,
    @Json(name = "name") val name : String,
    @Json(name = "description") val description : String,
    @Json(name = "favorite") val favorite : Boolean,
    @Json(name = "photo") val photo : String,

)
