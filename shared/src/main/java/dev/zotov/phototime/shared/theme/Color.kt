package dev.zotov.phototime.shared.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

var backgroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF0D0F33),
        Color(0xFF090620),
    ),
)

var PrimaryGradient = Brush.linearGradient(
    start = Offset(Float.POSITIVE_INFINITY, 0F),
    end = Offset(0F, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp,
    colors = listOf(
        Color(0xFF248AE8),
        Color(0xFF1C6DD9),
    ),
)

val White = Color(0xFFFEFDFF)
val LightGrey = Color(0xFFF8F8FF)
val Grey = Color(0xFFB2B3D4)
val TileColor = Color(0x08FFFFFF)
val Primary = Color(0xFF248AE8)
val ProgressBarColor = Color(0x40A0C5F3)
val LoadingElementColor = Color(0xFFFFFFFF).copy(alpha = 0.1f)
const val BackgroundPreviewColor = 0xFF0B0A29