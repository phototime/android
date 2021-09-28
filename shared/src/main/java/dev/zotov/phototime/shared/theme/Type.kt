package dev.zotov.phototime.shared.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.zotov.phototime.shared.R

var fontFamily = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
)

val Typography = Typography(
    defaultFontFamily = fontFamily,
)

@get:Composable
val Typography.title: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        color = Color(0xFFFEFDFF),
        textAlign = TextAlign.Center,
    )