package com.adyen.android.assignment.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adyen.android.assignment.presentation.planetary.detail.PlanetaryDetailScreen

@Composable
fun MainRoot() {
    val navController = rememberNavController()
    Surface(
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
                PlanetaryDetailScreen(date = date , navController= navController)
            }
        }
    }
}