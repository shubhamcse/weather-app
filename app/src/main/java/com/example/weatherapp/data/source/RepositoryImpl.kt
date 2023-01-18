package com.example.weatherapp.data.source

import com.example.weatherapp.data.remote.ApiService
import com.example.weatherapp.domain.error.ErrorEntity
import com.example.weatherapp.domain.error.ErrorHandler
import com.example.weatherapp.domain.model.WeatherResponse
import com.example.weatherapp.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val errorHandler: ErrorHandler
) : Repository {

    override suspend fun getWeatherData(location: String): Result<WeatherResponse> {
        runCatching {
            apiService.getWeatherData(location)
        }.onSuccess {
            return Result.Success(it)
        }.onFailure {
            return Result.Error(errorHandler.getError(it))
        }
        return Result.Error(ErrorEntity.Unknown)
    }


}