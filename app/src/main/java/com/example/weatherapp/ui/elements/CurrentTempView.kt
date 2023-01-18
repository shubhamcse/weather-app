package com.example.weatherapp.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Typography

@Composable
fun CurrentTempView(currentTemp: Float) {
    Row(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.margin_xlarge))
    ) {
        Text(
            style = Typography.h1,
            text = currentTemp.toInt().toString()
        )

        Text(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_xlarge)),
            style = Typography.body1,
            text = stringResource(R.string.weather_units),
        )
    }
    Text(
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_xlarge)),
        style = Typography.body2,
        text = stringResource(R.string.weather_today),
    )
}
