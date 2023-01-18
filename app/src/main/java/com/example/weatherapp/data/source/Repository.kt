package com.example.weatherapp.data.source

import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.Result
import javax.inject.Singleton

@Singleton
interface Repository {
    suspend fun getWeatherData(
        location: String
    ): Result<WeatherResponse>

}