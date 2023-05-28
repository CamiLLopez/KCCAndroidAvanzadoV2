package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource

import javax.inject.Inject

class RepositoryLoginImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): RepositoryLogin {

    override suspend fun login(credentials: String): String? {
        val response = remoteDataSource.login(credentials)

        if( response.isSuccessful){
            remoteDataSource.token = response.body().toString()
            return response.body()

        }else{
            return null
        }

    }
}