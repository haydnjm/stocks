package com.haydnjm.stocks.android.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.haydnjm.stocks.Greeting
import com.haydnjm.stocks.android.ui.theme.StocksTheme

@Composable
fun StocksApp() {
    StocksTheme {
        Surface(modifier = Modifier.fillMaxHeight()) {
            Text(Greeting().greeting())
        }
    }
}