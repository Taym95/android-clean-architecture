package com.adyen.android.assignment.app.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val Blue = Color(0xFF252941)
val DarkBlue = Color(0xFF4E0EEA)
val CardDark = Color(0xFF3B3E43)
val BackgroundDark = Color(0xFF24292E)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

val DarkTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        color = White,
        fontSize = 21.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        color = White,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Medium,
        color = White,
        fontSize = 14.sp
    ),
)

private val DarkColorPalette = darkColors(
    primary = Blue,
    primaryVariant = Blue,
    onPrimary = White,
    secondary = DarkBlue,
    secondaryVariant = DarkBlue,
    onSecondary = Black,
    background = BackgroundDark,
    onBackground = BackgroundDark,
    surface = CardDark,
    onSurface = CardDark
)


val PlanetaryColors: Colors
    @Composable get() = MaterialTheme.colors

val PlanetaryTypography: Typography
    @Composable get() = MaterialTheme.typography

@Composable
fun PlanetaryTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = DarkTypography,
        shapes = Shapes,
        content = content
    )
}