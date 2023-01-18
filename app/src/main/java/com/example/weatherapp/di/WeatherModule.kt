package com.example.weatherapp.di

import com.example.weatherapp.data.source.Repository
import com.example.weatherapp.data.source.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherModule {
    @Binds
    abstract fun bindWeatherRepo(repo: RepositoryImpl): Repository
}