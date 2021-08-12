package com.haydnjm.stocks.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.haydnjm.stocks.Greeting
import com.haydnjm.stocks.android.data.AuthViewModel
import com.haydnjm.stocks.android.ui.theme.StocksTheme

@Composable
fun StocksApp() {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    val authViewModel = AuthViewModel(LocalContext.current)

    val authenticated = authViewModel.getAuthenticationStatus()

    if (authenticated) {
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
    } else {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("You need to log in!")
            Button("Log in!")
            /**
             * TODO: Next steps:
             *  - Add loginByEmail to auth view model, repo and api,
             *  - Add loginByEmail to TS API - return user with fName, lName, email
             *  - Handle user - redirect to home page
              */


        }
    }

}

@Preview
@Composable
fun StocksAppPreview() {
    StocksApp()
}
