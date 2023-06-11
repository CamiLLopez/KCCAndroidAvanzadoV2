package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregaandroidavanzadov2.data.Repository
import com.example.entregaandroidavanzadov2.utils.generateHerosList
import com.example.entregaandroidavanzadov2.utils.getOrAwaitValue
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HerosViewModelTest{


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HerosViewModel

    private lateinit var repository: Repository

    private val fakeMainThread = newSingleThreadContext("UI thread")

    @Before
    fun setUp(){
        Dispatchers.setMain(fakeMainThread)
        repository = mockk()
        viewModel = HerosViewModel(repository)
    }

    @Test
    fun `WHEN getHeros EXPECT successful response`() = runTest {

        //Given
        val valueExpected = generateHerosList(16)
        coEvery { repository.getHeros() } returns valueExpected

        //When
        viewModel.getHeros()
        val liveData = viewModel.heros.getOrAwaitValue()
        //Then
         Truth.assertThat(liveData).containsExactlyElementsIn(valueExpected)
    }

    @Test
    fun `WHEN getHeros EXPECT error response`(){

        //Given
        coEvery { repository.getHeros() } returns emptyList()
        //When
        viewModel.getHeros()
        val liveData = viewModel.heros.getOrAwaitValue()
        //Then
        Truth.assertThat(liveData).isEmpty()
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}