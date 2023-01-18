package com.example.weatherapp.data.source

import com.example.weatherapp.data.remote.ApiService
import com.example.weatherapp.domain.error.ErrorEntity
import com.example.weatherapp.domain.error.ErrorHandler
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
internal class RepositoryImplTest {

    private lateinit var repositoryImpl: RepositoryImpl
    private val service: ApiService = mockk(relaxed = true)
    private val errorHandler: ErrorHandler = mockk(relaxed = true)
    private val apiResponse: WeatherResponse = mockk(relaxed = true)
    private val location = "London"


    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        repositoryImpl =
            RepositoryImpl(
                apiService = service,
                errorHandler = errorHandler
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get weather Data should respective call method on service with right params`() {
        runTest {
            repositoryImpl.getWeatherData(location)
            coVerify {
                service.getWeatherData(
                    location
                )
            }
        }
    }

    @Test
    fun `get Weather Data should return success with correct response when service returns success`() {
        runTest {
            coEvery {
                service.getWeatherData(location)
            } returns apiResponse
            val result = repositoryImpl.getWeatherData(location)

            Assert.assertEquals(Result.Success(apiResponse), result)
        }
    }

    @Test
    fun `get Weather Data should return error result when service returns exception`() {
        runTest {
            coEvery {
                service.getWeatherData(location)
            } throws IOException()

            coEvery { errorHandler.getError(any()) } returns ErrorEntity.Unknown

            val result = repositoryImpl.getWeatherData(location)

            Assert.assertEquals(Result.Error<ErrorEntity>(ErrorEntity.Unknown), result)
        }
    }
}