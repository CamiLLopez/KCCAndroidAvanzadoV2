package com.example.entregaandroidavanzadov2.data.remote.fakes

import com.example.entregaandroidavanzadov2.data.remote.IDragonBallAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

open class BaseNetworkMockTest {

    lateinit var api: IDragonBallAPI
    private lateinit var mockWebServer: MockWebServer
    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = DragonBallApiMockDispatcher()
        mockWebServer.start()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(IDragonBallAPI::class.java)

    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()

    }

}