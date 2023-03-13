package com.vpdevs.bmicaluculator.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkRed,
    primaryVariant = DarkRed,
    secondary = DarkRed,
    onBackground = Black
)

private val LightColorPalette = darkColors(
    primary = DarkRed,
    primaryVariant = DarkRed,
    secondary = DarkRed,
    onBackground = Black


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BMICalculatorTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors =colors ,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}