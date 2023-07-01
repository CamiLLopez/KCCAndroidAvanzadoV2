package com.example.entregaandroidavanzadov2.data.remote.fakes

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class LoginApiMockDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {

            return when(request.path){
                "api/auth/login" -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody("eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjFFQzk5RDAzLTRDMTMtNEZFNS1CMERBLUJCQjQxRkFFMzdERiIsImVtYWlsIjoiY2FtaWxhbGxvcGV6OTVAZ21haWwuY29tIiwiZXhwaXJhdGlvbiI6NjQwOTIyMTEyMDB9.mCt5VQOCzuB4cdv8sm_RDmEGoINTeS3tCZlZ2l2pLYY")
                }
                else -> MockResponse().throttleBody(1024, 5, TimeUnit.SECONDS)
            }
        }
}
