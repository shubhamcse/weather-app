package com.example.weatherapp.data.remote

import com.example.weatherapp.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("q") location: String,
        @Query("appId") apiKey: String = "fb59333d321322e6c4b2cd2a3760a5ef",
        @Query("units") units: String = "metric"
    ): WeatherResponse
}