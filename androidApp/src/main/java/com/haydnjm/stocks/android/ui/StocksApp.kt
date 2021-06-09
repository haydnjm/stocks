package com.haydnjm.stocks.android.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.haydnjm.stocks.Greeting
import com.haydnjm.stocks.android.ui.theme.StocksTheme

@Composable
fun StocksApp() {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()

    StocksTheme {
        Scaffold(
            topBar = { TopNav(navigate = { route -> navController.navigate(route) }) },
            bottomBar = { BottomNav(navigate = { route -> navController.navigate(route) }) }
        ) { paddingValues ->
            Box(Modifier.padding(paddingValues)) {
                StocksNavigation(navHostController = navController)
            }
        }
    }
}

@Preview
@Composable
fun StocksAppPreview() {
    StocksApp()
}
