package com.example.weatherapp.ui.weather

data class WeatherUiState(
    val isLoading: Boolean = false,
    val currentTemp: Float? = null,
    val feelsLikeTemp: Float? = null,
    val currentWeatherDescription: String = "",
    val extraWeatherDetails: Map<String, String> = emptyMap(),
    val city: String = "",
    val userMessageID: Int? = null,
)

