package com.example.entregaandroidavanzadov2.utils

import com.example.entregaandroidavanzadov2.data.Repository
import com.example.entregaandroidavanzadov2.data.RepositoryImpl
import com.example.entregaandroidavanzadov2.data.RepositoryLogin
import com.example.entregaandroidavanzadov2.data.RepositoryLoginImpl
import com.example.entregaandroidavanzadov2.data.local.ILocalDataSource
import com.example.entregaandroidavanzadov2.data.local.LocalDataSourceImpl
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindsLoginRepository(repositoryLoginImpl: RepositoryLoginImpl): RepositoryLogin

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): ILocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}