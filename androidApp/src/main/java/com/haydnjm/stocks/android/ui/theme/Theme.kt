package com.haydnjm.stocks.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightThemeColors = lightColors(
    primary = purple200,
    primaryVariant = purple500,
    secondary = green500,
    secondaryVariant = green300,
    surface = blue900,
    onSurface = blue500,
)

private val DarkThemeColours = darkColors()

@Composable
fun StocksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if(darkTheme) DarkThemeColours else LightThemeColors,
        content = content,
    )
}