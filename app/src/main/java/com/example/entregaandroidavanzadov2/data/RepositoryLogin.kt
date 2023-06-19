package com.example.entregaandroidavanzadov2.data

interface RepositoryLogin {
    suspend fun login(credentials: String): String?
}