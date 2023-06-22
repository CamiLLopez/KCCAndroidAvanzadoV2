package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.fakes.FakeRemoteDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
class RepositoryLoginImplTest{

    private lateinit var repositoryLoginImpl: RepositoryLoginImpl

    private lateinit var remoteDataSource: RemoteDataSource


    @Before
    fun setUp() {
        remoteDataSource = FakeRemoteDataSource()
        repositoryLoginImpl = RepositoryLoginImpl(remoteDataSource)
    }

    @Test
    fun `WHEN no login EXPECT false response`()= runTest{

        val valueActual = repositoryLoginImpl.login("badCredentials")

        assert(valueActual.isNullOrBlank())

    }

    @Test
    fun `WHEN login EXPECT true response`()= runTest{

        val valueActual = repositoryLoginImpl.login("Basic goodCredentials")

        Truth.assertThat(valueActual).isNotNull()

    }

    @After
    fun tearDown() {
    }


}

