package com.example.weatherapp.domain.error

interface ErrorHandler {

    fun getError(throwable: Throwable?): ErrorEntity
}