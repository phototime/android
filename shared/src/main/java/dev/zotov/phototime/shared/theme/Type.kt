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
val Typography.headline: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        color = White,
        textAlign = TextAlign.Center,
    )

@get:Composable
val Typography.subtitle: TextStyle
    get() = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = Grey,
        textAlign = TextAlign.Center,
    )

@get:Composable
val Typography.propertyName: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = Grey,
        textAlign = TextAlign.Center,
    )

@get:Composable
val Typography.propertyValue: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        color = White,
        textAlign = TextAlign.Center,
    )

@get:Composable
val Typography.tileHeader: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = fontFamily,
        color = White,
        textAlign = TextAlign.Left,
    )

@get:Composable
val Typography.primaryCaption: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = Primary,
        textAlign = TextAlign.Right,
    )

@get:Composable
val Typography.timer: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Left,
    )

@get:Composable
val Typography.timeDuration: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = Grey,
        textAlign = TextAlign.Right,
    )

@get:Composable
val Typography.timeDurationAbbreviation: TextStyle
    get() = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = Grey,
        textAlign = TextAlign.Right,
    )

@get:Composable
val Typography.title: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fontFamily,
        color = LightGrey,
        textAlign = TextAlign.Left,
    )