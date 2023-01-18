package com.example.weatherapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

val fonts = FontFamily(
    Font(R.font.raleway_thin, weight = FontWeight.Thin),
    Font(R.font.raleway_light, weight = FontWeight.Light),
    Font(R.font.raleway_medium, weight = FontWeight.Medium),
    Font(R.font.raleway_regular, weight = FontWeight.Normal),
    Font(R.font.raleway_bold, weight = FontWeight.Bold),
    Font(R.font.raleway_black, weight = FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.LightGray
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = Color.LightGray,
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 64.sp,
        color = Color.White
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        color = Color.LightGray
    ),
)
/* Other default text styles to override
button = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
),
caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/



