package com.example.entregaandroidavanzadov2.data

import com.example.entregaandroidavanzadov2.data.mappingClasses.LocalToSuperHeroMapper
import com.example.entregaandroidavanzadov2.data.mappingClasses.RemoteLocationsToLocationHero
import com.example.entregaandroidavanzadov2.data.mappingClasses.RemoteToLocalMapper
import com.example.entregaandroidavanzadov2.data.local.fakes.FakeLocalDataSource
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.utils.generateHeroLocationsResponse
import com.example.entregaandroidavanzadov2.utils.generateHerosResponse
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    private lateinit var repositoryImpl: RepositoryImpl

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToSuperHeroMapper: LocalToSuperHeroMapper
    private lateinit var remoteLocationsToLocationHero: RemoteLocationsToLocationHero


    @Before
    fun setUp() {
        remoteDataSource = mockk()

        localDataSource = FakeLocalDataSource()
        remoteToLocalMapper = RemoteToLocalMapper()
        localToSuperHeroMapper = LocalToSuperHeroMapper()
        remoteLocationsToLocationHero = RemoteLocationsToLocationHero()

        repositoryImpl = RepositoryImpl(localDataSource, remoteDataSource, remoteToLocalMapper, localToSuperHeroMapper, remoteLocationsToLocationHero)
    }

    @Test
    fun `WHEN getHeros EXPECT local is empty return network`() = runTest{

        coEvery { remoteDataSource.getHeros() } returns generateHerosResponse(16)

        val valueActual = repositoryImpl.getHeros()

        assert(valueActual.isNotEmpty())
    }


    @Test
    fun `WHEN getHeros EXPECT successful local response next call`() = runTest{

        coEvery { remoteDataSource.getHeros() } returns emptyList()

        val valueActual = repositoryImpl.getHeros()

        assert(valueActual.isNotEmpty())
    }

    @Test
    fun `WHEN getHero EXPECT successful local response`() = runTest{

        val heroFromLocal = repositoryImpl.getHero("12345")

        Truth.assertThat(heroFromLocal).isNotNull()

    }

    @Test
    fun `WHEN getLocations EXPECT successful remote response`() = runTest{

        coEvery { remoteDataSource.getHeroLocations("12345") } returns generateHeroLocationsResponse(4)

        val heroLocations = repositoryImpl.getLocations("12345")

        Truth.assertThat(heroLocations).isNotNull()
    }

    @Test
    fun `WHEN getLocations EXPECT empty_null remote response`() = runTest{

        coEvery { remoteDataSource.getHeroLocations("12345") } returns emptyList()

        val heroLocations = repositoryImpl.getLocations("12345")

        Truth.assertThat(heroLocations).isNull()
    }

    @After
    fun tearDown() {
    }
}