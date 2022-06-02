package com.adyen.android.assignment.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.adyen.android.assignment.presentation.planetary.list.PlanetaryScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            val modifier = Modifier.padding(it)
            PlanetaryScreen(
                modifier = modifier,
                navController = navController
            )
        }
    )
}
