package com.example.weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.elements.*
import com.example.weatherapp.ui.weather.WeatherUiState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeatherAppHomeScreen(
    weatherUiState: WeatherUiState,
    onSearchWeatherForLocation: (String) -> Unit,
    onResetErrorMessage: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    weatherUiState.userMessageID?.let {
        SnackBarUI(scaffoldState, userMessageID = it, onResetErrorMessage)
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            MaterialTheme.colors.primaryVariant
                        )
                    )
                )
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchWeatherUI(onSearchWeatherForLocation)
            CityNameUI(weatherUiState.city)
            weatherUiState.currentTemp?.let { CurrentTempView(it) }
            Spacer(modifier = Modifier.weight(1f))
            GridViewForExtraWeatherDetails(weatherUiState)

        }

    }


}