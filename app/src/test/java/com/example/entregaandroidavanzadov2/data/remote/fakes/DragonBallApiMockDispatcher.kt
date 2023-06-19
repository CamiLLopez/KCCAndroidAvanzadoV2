package com.example.entregaandroidavanzadov2.data.remote.fakes

import com.google.common.io.Resources
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.File
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class DragonBallApiMockDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {

        return when(request.path){
            "/api/heros/all" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(getJson("json/heros.json"))
            }
            else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
        }
    }

    private fun getJson(path: String): String {

        var uri = Resources.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())

    }

}
