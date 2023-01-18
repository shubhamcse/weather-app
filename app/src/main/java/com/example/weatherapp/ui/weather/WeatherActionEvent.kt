package com.example.weatherapp.ui.weather

sealed class WeatherActionEvent {
    data class GetWeatherForLocation(
        val location: String
    ) : WeatherActionEvent()

    object ResetErrorMessage : WeatherActionEvent()
}