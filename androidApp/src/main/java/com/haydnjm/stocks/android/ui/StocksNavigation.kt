package com.haydnjm.stocks.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.haydnjm.stocks.android.ui.screens.Account
import com.haydnjm.stocks.android.ui.screens.Home
import com.haydnjm.stocks.android.ui.screens.Triggers

enum class Routes {
    Home,
    Triggers,
    Account
}

@Composable
fun StocksNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home.name
    ) {
        composable(Routes.Home.name) {
            Home()
        }
        composable(Routes.Triggers.name) {
            Triggers()
        }
        composable(Routes.Account.name) {
            Account()
        }
    }
}