package com.example.entregaandroidavanzadov2.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entregaandroidavanzadov2.data.Repository
import com.example.entregaandroidavanzadov2.data.RepositoryLogin
import com.example.entregaandroidavanzadov2.data.RepositoryLoginImpl
import com.example.entregaandroidavanzadov2.data.remote.RemoteDataSource
import com.example.entregaandroidavanzadov2.data.remote.fakes.FakeRemoteDataSource
import com.example.entregaandroidavanzadov2.utils.generateHerosList
import com.example.entregaandroidavanzadov2.utils.generateLoginToken
import com.example.entregaandroidavanzadov2.utils.getOrAwaitValue
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class LoginViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel
    private lateinit var repositoryLogin: RepositoryLogin
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        remoteDataSource = FakeRemoteDataSource()
        repositoryLogin = RepositoryLoginImpl(remoteDataSource)
        viewModel = LoginViewModel(repositoryLogin)
    }

    @Test
    fun `WHEN login EXPECT successful response`() = runTest {

        //When
        viewModel.login("camilallopez95@gmail.com", "Cl1995#")
        val liveData = viewModel.loginResult.getOrAwaitValue()
        //Then
        Truth.assertThat(liveData).isTrue()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}