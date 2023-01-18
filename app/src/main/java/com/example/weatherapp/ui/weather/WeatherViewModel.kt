package com.example.weatherapp.ui.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.data.source.Repository
import com.example.weatherapp.domain.error.ErrorEntity
import com.example.weatherapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    var uiState by mutableStateOf(WeatherUiState())
        private set

    fun performAction(weatherActionEvent: WeatherActionEvent) {
        viewModelScope.launch {
            when (weatherActionEvent) {
                is WeatherActionEvent.GetWeatherForLocation -> {
                    uiState = when (val result = repository.getWeatherData(
                        weatherActionEvent.location
                    )) {

                        is Result.Success -> {
                            val response = result.data
                            uiState.copy(
                                currentTemp = response?.main?.temp?.toFloat(),
                                city = response?.name.toString(),
                                extraWeatherDetails = mapOf(
                                    "Temp Min (ºC)" to response?.main?.tempMin.toString(),
                                    "Temp Max (ºC)" to response?.main?.tempMax.toString(),
                                    "Feels Like (ºC)" to response?.main?.feelsLike.toString(),
                                    "Humidity (%)" to response?.main?.humidity.toString()
                                )
                            )
                        }
                        is Result.Error -> {
                            if (result.error == ErrorEntity.NotFound) {
                                uiState.copy(
                                    userMessageID = R.string.city_not_found
                                )
                            } else {
                                uiState.copy(
                                    userMessageID = R.string.generic_error
                                )
                            }
                        }
                    }
                }
                WeatherActionEvent.ResetErrorMessage -> {
                    uiState = uiState.copy(userMessageID = null)
                }
            }
        }
    }
}