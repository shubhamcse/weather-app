package com.example.weatherapp.di


import com.example.weatherapp.domain.error.ErrorHandler
import com.example.weatherapp.domain.error.GeneralErrorHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindErrorHandler(errorHandlerImpl: GeneralErrorHandlerImpl): ErrorHandler
}