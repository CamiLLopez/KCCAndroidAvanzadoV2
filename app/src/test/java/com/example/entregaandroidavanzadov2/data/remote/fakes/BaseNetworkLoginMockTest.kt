package com.example.entregaandroidavanzadov2.data.remote.fakes

import com.example.entregaandroidavanzadov2.data.remote.IDragonBallAPI
import com.example.entregaandroidavanzadov2.data.remote.ILoginAPI
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseNetworkLoginMockTest {

    lateinit var loginAPI: ILoginAPI
    private lateinit var mockWebServer: MockWebServer
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = LoginApiMockDispatcher()
        mockWebServer.start()

        loginAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ILoginAPI::class.java)

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()

    }

}