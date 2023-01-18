package com.example.weatherapp.ui.elements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.BG

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun SearchWeatherUI(onSearchWeatherForLocation: (String) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = BG,
            cursorColor = Color.LightGray,
            disabledLabelColor = Color.Gray,
            leadingIconColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            text = it
        },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_medium)),
        singleLine = true,
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.margin_large))
                    .size(dimensionResource(id = R.dimen.margin_xlarge)),
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onSearchWeatherForLocation.invoke(text.text)
        }),
    )
}