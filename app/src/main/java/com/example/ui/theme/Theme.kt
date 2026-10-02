package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val LocalIsLightTheme = staticCompositionLocalOf { false }
val LocalThemeMode = staticCompositionLocalOf { "midnight" }

object CrownColors {
    val themeMode: String
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeMode.current

    val isNeon: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalThemeMode.current == "neon"

    val isLight: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalIsLightTheme.current

    val DeepVoid: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFFEEF3FB)
            isNeon -> Color(0xFF070214)
            else -> Color(0xFF05050A)
        }

    val NebulaCore: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFFD9E6FA)
            isNeon -> Color(0xFF2A0E52)
            else -> Color(0xFF15162E)
        }

    val ShellTop: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFFFFFFFF)
            isNeon -> Color(0xFF1D0F3B)
            else -> Color(0xFF131427)
        }

    val ShellBottom: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFFEAF0FA)
            isNeon -> Color(0xFF080316)
            else -> Color(0xFF070811)
        }

    val AmbientViolet: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF6B4EFF)
            isNeon -> Color(0xFFE028FF)
            else -> Color(0xFF5B47E9)
        }

    val AmbientCyan: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF00B8A9)
            isNeon -> Color(0xFF00FFE5)
            else -> Color(0xFF14D6D4)
        }

    val NeonCyan: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF008A7C)
            isNeon -> Color(0xFF00FFF0)
            else -> Color(0xFF56F2DF)
        }

    val NeonAqua = Color(0xFFA1EFFF)

    val PositiveMint: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF008771)
            isNeon -> Color(0xFF19FFCA)
            else -> Color(0xFF50E2C7)
        }

    val AccentViolet: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF6338CA)
            isNeon -> Color(0xFFFF61F6)
            else -> Color(0xFFBE94FF)
        }

    val DeepViolet = Color(0xFF9A7BFF)
    val WarmAmber = Color(0xFFFFB36E)
    val CoralAlert = Color(0xFFFF8B8B)
    val CoralDot = Color(0xFFFF9F87)

    val TextPrimary: Color
        @Composable
        @ReadOnlyComposable
        get() = if (isLight) Color(0xFF0E1326) else Color(0xFFF6F7FF)

    val TextSecondary: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF3A4362)
            isNeon -> Color(0xFFD2C4F7)
            else -> Color(0xFFAAB0C9)
        }

    val TextMuted: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF586284)
            isNeon -> Color(0xFFA390D4)
            else -> Color(0xFF777E9F)
        }

    val TextSubtle: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xFF7580A0)
            isNeon -> Color(0xFF7B68AD)
            else -> Color(0xFF565D77)
        }

    val SurfaceGlass: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0xD9FFFFFF)
            isNeon -> Color(0x242A1250)
            else -> Color(0x0DFFFFFF)
        }

    val SurfaceBorder: Color
        @Composable
        @ReadOnlyComposable
        get() = when {
            isLight -> Color(0x220E1326)
            isNeon -> Color(0x4200FFF0)
            else -> Color(0x14FFFFFF)
        }

    val DarkInk = Color(0xFF061015)
    val EthBlue = Color(0xFF627EEA)
    val SolPurple = Color(0xFF9945FF)
    val SolGreen = Color(0xFF14F195)
    val UsdtTeal = Color(0xFF26A17B)
}

private val MidnightColorScheme = darkColorScheme(
    primary = Color(0xFF56F2DF),
    onPrimary = Color(0xFF061015),
    primaryContainer = Color(0xFF122C32),
    onPrimaryContainer = Color(0xFF56F2DF),
    secondary = Color(0xFFBE94FF),
    onSecondary = Color(0xFF1E1335),
    secondaryContainer = Color(0xFF271D44),
    onSecondaryContainer = Color(0xFFE7DBFF),
    tertiary = Color(0xFFFFB36E),
    background = Color(0xFF05050A),
    onBackground = Color(0xFFF6F7FF),
    surface = Color(0xFF131427),
    onSurface = Color(0xFFF6F7FF),
    surfaceVariant = Color(0xFF1A1B34),
    onSurfaceVariant = Color(0xFFAAB0C9),
    outline = Color(0xFF777E9F),
    outlineVariant = Color(0xFF2A2D4A),
    error = Color(0xFFFF8B8B)
)

private val NeonColorScheme = MidnightColorScheme.copy(
    primary = Color(0xFF33FFF0),
    secondary = Color(0xFFD0A8FF),
    surface = Color(0xFF171834),
    surfaceVariant = Color(0xFF212246)
)

private val LightCrownColorScheme = lightColorScheme(
    primary = Color(0xFF009B8D),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4FBF6),
    onPrimaryContainer = Color(0xFF003832),
    secondary = Color(0xFF6B3FD4),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEDE4FF),
    onSecondaryContainer = Color(0xFF250B61),
    tertiary = Color(0xFFD97724),
    background = Color(0xFFF3F6FC),
    onBackground = Color(0xFF0E1326),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF0E1326),
    surfaceVariant = Color(0xFFE6ECF7),
    onSurfaceVariant = Color(0xFF3E4663),
    outline = Color(0xFF5E6787),
    outlineVariant = Color(0xFFD0D8E8),
    error = Color(0xFFD93838)
)

val CrownTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 46.sp,
        lineHeight = 52.sp,
        letterSpacing = (-2.2).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.8).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 21.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.5).sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 1.2.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.8.sp
    )
)

val CrownShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(18.dp),
    extraLarge = RoundedCornerShape(22.dp)
)

@Composable
fun CrownVaultTheme(
    themeMode: String = "midnight",
    content: @Composable () -> Unit
) {
    // "auto" applies the adaptive daylight/high-contrast theme so all three Appearance options
    // (Midnight, Neon, Auto) produce a distinct global visual transformation.
    val useLight = when (themeMode) {
        "auto", "light" -> true
        else -> false
    }
    val scheme = when {
        useLight -> LightCrownColorScheme
        themeMode == "neon" -> NeonColorScheme
        else -> MidnightColorScheme
    }

    CompositionLocalProvider(
        LocalIsLightTheme provides useLight,
        LocalThemeMode provides themeMode
    ) {
        MaterialTheme(
            colorScheme = scheme,
            typography = CrownTypography,
            shapes = CrownShapes,
            content = content
        )
    }
}
