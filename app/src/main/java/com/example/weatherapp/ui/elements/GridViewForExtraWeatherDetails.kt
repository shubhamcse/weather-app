package com.example.weatherapp.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.BG
import com.example.weatherapp.ui.theme.Typography
import com.example.weatherapp.ui.weather.WeatherUiState

@Composable
fun GridViewForExtraWeatherDetails(weatherUiState: WeatherUiState) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.margin_xxlarge)),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_large)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin_large))
    ) {
        items(weatherUiState.extraWeatherDetails.toList()) {
            Box(
                modifier = Modifier
                    .background(BG)
                    .fillMaxSize()
                    .padding(top = dimensionResource(id = R.dimen.margin_medium),
                        bottom = dimensionResource(id = R.dimen.margin_xlarge))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small)),
                        text = it.first
                    )
                    Text(
                        style = Typography.h2,
                        text = it.second
                    )
                }
            }
        }
    }
}