package com.example.weatherapp.utils

import com.example.weatherapp.domain.error.ErrorEntity


sealed class Result<T> {

    data class Success<T>(val data: T?) : Result<T>()

    data class Error<T>(val error: ErrorEntity) : Result<T>()
}