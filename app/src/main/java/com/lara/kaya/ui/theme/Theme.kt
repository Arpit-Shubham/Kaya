package com.lara.kaya.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AmoledDarkColorScheme = darkColorScheme(
    primary = PureWhite,
    onPrimary = AmoledBlack,
    secondary = PureWhite,
    onSecondary = AmoledBlack,
    background = AmoledBlack,
    onBackground = PureWhite,
    surface = DarkCardBg,
    onSurface = PureWhite,
    outline = DarkBorder
)

private val PaperLightColorScheme = lightColorScheme(
    primary = AmoledBlack,
    onPrimary = PureWhite,
    secondary = AmoledBlack,
    onSecondary = PureWhite,
    background = PureWhite,
    onBackground = AmoledBlack,
    surface = LightCardBg,
    onSurface = AmoledBlack,
    outline = LightBorder
)

@Composable
fun KayaTheme(
    inverted: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (inverted) PaperLightColorScheme else AmoledDarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
