package com.example.weatherapp.ui.elements

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SnackBarUI(
    scaffoldState: ScaffoldState,
    userMessageID: Int,
    resetErrorMessage: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    userMessageID.let {
        val message = stringResource(id = userMessageID)
        LaunchedEffect(userMessageID) {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message
                )
                resetErrorMessage.invoke()
            }
        }
    }
}