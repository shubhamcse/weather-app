package com.example.weatherapp.ui.weather

import com.example.weatherapp.R
import com.example.weatherapp.data.source.Repository
import com.example.weatherapp.domain.error.ErrorEntity
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel
    private val repository: Repository = mockk(relaxed = true)
    private val apiResponse: WeatherResponse = mockk(relaxed = true)
    private val location = "London"

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = WeatherViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get Weather Data Data Event should respective call method on repository with right params`() {
        runTest {
            viewModel.performAction(WeatherActionEvent.GetWeatherForLocation(location))
            coVerify {
                repository.getWeatherData(location)
            }
        }
    }

    @Test
    fun `should update UI state with list if response is success on Get Weather data`() {
        runTest {
            coEvery {
                repository.getWeatherData(location)
            } returns Result.Success(apiResponse)

            viewModel.performAction(WeatherActionEvent.GetWeatherForLocation(location = location))

            val actualState = viewModel.uiState
            val expectedState = WeatherUiState(
                currentTemp = apiResponse.main?.temp?.toFloat(),
                city = apiResponse.name.toString(),
                extraWeatherDetails = mapOf(
                    "Temp Min (ºC)" to apiResponse.main?.tempMin.toString(),
                    "Temp Max (ºC)" to apiResponse.main?.tempMax.toString(),
                    "Feels Like (ºC)" to apiResponse.main?.feelsLike.toString(),
                    "Humidity (%)" to apiResponse.main?.humidity.toString()
                )
            )

            Assert.assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun `should update UI state with correct error message if response is error on Get Weather Data other than not found`() {
        runTest {
            coEvery {
                repository.getWeatherData(location)
            } returns Result.Error(ErrorEntity.Unknown)

            viewModel.performAction(WeatherActionEvent.GetWeatherForLocation(location = location))

            val actualState = viewModel.uiState
            val expectedState = WeatherUiState(
                userMessageID = R.string.generic_error
            )

            Assert.assertEquals(expectedState, actualState)
        }
    }


    @Test
    fun `should update UI state with correct error message if response is error on Get Weather Data with 404 not found`() {
        runTest {
            coEvery {
                repository.getWeatherData(location)
            } returns Result.Error(ErrorEntity.NotFound)

            viewModel.performAction(WeatherActionEvent.GetWeatherForLocation(location = location))

            val actualState = viewModel.uiState
            val expectedState = WeatherUiState(
                userMessageID = R.string.city_not_found
            )

            Assert.assertEquals(expectedState, actualState)
        }
    }

    @Test
    fun `should update UI state with error message null if on ResetErrorMessage is fired & already user message was assigned`() {
        runTest {
            coEvery {
                repository.getWeatherData(location)
            } returns Result.Error(ErrorEntity.Unknown)

            viewModel.performAction(WeatherActionEvent.GetWeatherForLocation(location = location))
            val actualState = viewModel.uiState
            val expectedState = WeatherUiState(
                userMessageID = R.string.generic_error
            )

            Assert.assertEquals(expectedState, actualState)

            viewModel.performAction(WeatherActionEvent.ResetErrorMessage)

            val newActualState = viewModel.uiState
            val newExpectedState = WeatherUiState(
                userMessageID = null
            )

            Assert.assertEquals(newExpectedState, newActualState)
        }
    }
}