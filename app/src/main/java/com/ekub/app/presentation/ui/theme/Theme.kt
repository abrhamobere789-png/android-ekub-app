package com.ekub.app.presentation.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    // Primary - Blue
    primary = PrimaryBlue,
    onPrimary = TertiaryWhite,
    primaryContainer = PrimaryBlueLight,
    onPrimaryContainer = PrimaryBlueDark,

    // Secondary - Green
    secondary = SecondaryGreen,
    onSecondary = TertiaryWhite,
    secondaryContainer = SecondaryGreenLight,
    onSecondaryContainer = SecondaryGreenDark,

    // Tertiary - White
    tertiary = TertiaryWhite,
    onTertiary = TextDark,
    tertiaryContainer = BackgroundWhite,
    onTertiaryContainer = TextDark,

    // Background
    background = BackgroundWhite,
    onBackground = TextDark,

    // Surface
    surface = SurfaceWhite,
    onSurface = TextDark,

    // Error
    error = ErrorRed,
    onError = TertiaryWhite,
    errorContainer = Color(0xFFF8D7DA),
    onErrorContainer = ErrorRed,

    // Outline
    outline = BorderColor,
    outlineVariant = DividerColor,

    // Scrim
    scrim = Color(0x00000000)
)

@Composable
fun AndroidEkubAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
