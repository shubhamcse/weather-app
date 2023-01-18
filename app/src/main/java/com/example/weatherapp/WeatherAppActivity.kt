package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.ui.screens.WeatherAppHomeScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.weather.WeatherActionEvent
import com.example.weatherapp.ui.weather.WeatherUiState
import com.example.weatherapp.ui.weather.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherAppActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherAppHomeScreen(
                        weatherUiState = weatherViewModel.uiState,
                        onSearchWeatherForLocation = {
                        weatherViewModel.performAction(
                            WeatherActionEvent.GetWeatherForLocation(
                                it
                            )
                        )
                    },
                        onResetErrorMessage = {
                            weatherViewModel.performAction(WeatherActionEvent.ResetErrorMessage)
                        })
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    WeatherAppTheme {
        WeatherAppHomeScreen(WeatherUiState(
            city = "Stockholm",
            currentTemp = 1.5f
        ), {}, {})
    }
}