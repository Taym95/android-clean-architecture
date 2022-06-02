package com.adyen.android.assignment.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adyen.android.assignment.app.theme.PlanetaryColors
import com.adyen.android.assignment.app.theme.PlanetaryTheme
import com.adyen.android.assignment.presentation.planetary.detail.PlanetaryDetailScreen

@Composable
fun MainRoot() {
    val navController = rememberNavController()
    PlanetaryTheme() {
        Surface(
            color = PlanetaryColors.background,
            modifier = Modifier.fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                startDestination = "home_screen"
            ) {
                composable("home_screen") {
                    HomeScreen(navController)
                }
                composable(
                    "planetary/{date}/", arguments = listOf(
                        navArgument("date") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val date = it.arguments?.getString("date")!!
                    PlanetaryDetailScreen(date = date, navController = navController)
                }
            }
        }
    }

}