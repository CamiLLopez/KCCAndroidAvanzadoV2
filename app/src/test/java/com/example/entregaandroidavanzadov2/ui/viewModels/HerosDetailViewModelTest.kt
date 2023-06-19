package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregaandroidavanzadov2.data.Repository
import com.example.entregaandroidavanzadov2.utils.generateHeroLocationsHero
import com.example.entregaandroidavanzadov2.utils.generateHeroLocationsResponse
import com.example.entregaandroidavanzadov2.utils.generateHerosList
import com.example.entregaandroidavanzadov2.utils.generateLocalHero
import com.example.entregaandroidavanzadov2.utils.generateSuperHero
import com.example.entregaandroidavanzadov2.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HerosDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HerosDetailViewModel

    private lateinit var repository: Repository

    private val fakeMainThread = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(fakeMainThread)
        repository = mockk()
        viewModel = HerosDetailViewModel(repository)
    }


    @Test
    fun `WHEN getHero EXPECT successful response`() = runTest {

        //Given
        val valueExpected = generateSuperHero()
        coEvery { repository.getHero("963CA612-716B-4D08-991E-8B1AFF625A81") } returns valueExpected

        //When
        viewModel.getHero("963CA612-716B-4D08-991E-8B1AFF625A81")
        val liveData = viewModel.heroResult.getOrAwaitValue()
        //Then
        Truth.assertThat(liveData).isNotNull()
    }

    @Test
    fun `WHEN getLocationByHero EXPECT successful response`() = runTest{

        //Given
        coEvery { repository.getLocations("963CA612-716B-4D08-991E-8B1AFF625A81") } returns generateHeroLocationsHero(4)
        //When
        viewModel.getLocationsByHero("963CA612-716B-4D08-991E-8B1AFF625A81")
        val liveData = viewModel.locationResult.getOrAwaitValue()
        //Then
        Truth.assertThat(liveData).isNotEmpty()
    }

    @Test
    fun `WHEN getLocationByHero EXPECT empty response`() = runTest{

        //Given
        coEvery { repository.getLocations("963CA612-716B-4D08-991E-8B1AFF625A81") } returns emptyList()
        //When
        viewModel.getLocationsByHero("963CA612-716B-4D08-991E-8B1AFF625A81")
        val liveData = viewModel.locationResult.getOrAwaitValue()
        //Then
        Truth.assertThat(liveData).isEmpty()
    }



    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}