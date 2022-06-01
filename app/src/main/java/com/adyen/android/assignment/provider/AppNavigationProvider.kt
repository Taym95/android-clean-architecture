package com.adyen.android.assignment.provider

import androidx.navigation.NavController

class AppNavigationProvider constructor(
    private val navController: NavController
) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
    }
}