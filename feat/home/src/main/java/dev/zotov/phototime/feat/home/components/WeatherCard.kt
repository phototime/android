package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.zotov.phototime.shared.components.WeatherIcon
import dev.zotov.phototime.shared.components.WeatherIcons
import dev.zotov.phototime.shared.theme.*
import dev.zotov.phototime.feat.home.R

@Composable
fun WeatherCard(modifier: Modifier = Modifier, selected: Boolean) {
    Container(modifier = modifier, selected = selected) {
        WeatherIcon(icon = WeatherIcons.SunWithCloud, modifier = Modifier.width(50.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Time(text = "14:00")
            Spacer(modifier = Modifier.height(3.dp))
            Temperature(text = "32°")
        }
    }
}

@Composable
private fun Time(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = White.copy(alpha = 0.6f),
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
        )
    )
}

@Composable
private fun Temperature(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
    )
}

@Composable
private fun Container(modifier: Modifier = Modifier, selected: Boolean, content: @Composable RowScope.() -> Unit) {
    val backgroundModifier = if (selected) Modifier else Modifier.background(color = TileColor)

    @Composable
    fun RowContainer(modifier: Modifier = Modifier) {
        Row(
            modifier = Modifier
                .composed { modifier }
                .clip(MaterialTheme.shapes.large)
                .width(137.dp)
                .height(72.dp)
                .composed { backgroundModifier }
                .padding(horizontal = 20.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }

    if (selected) {
        val painter = painterResource(id = R.drawable.selected_weather_card_background)

        Box(modifier = modifier) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .width(137.dp)
                    .height(72.dp)
            )
            RowContainer()
        }
    } else {
        RowContainer(modifier = modifier)
    }

}

@Composable
@Preview(backgroundColor = BackgroundPreviewColor, showBackground = true)
private fun PreviewSelected() {
    PhototimeTheme {
        WeatherCard(selected = true)
    }
}

@Composable
@Preview(backgroundColor = BackgroundPreviewColor, showBackground = true)
private fun PreviewNotSelected() {
    PhototimeTheme {
        WeatherCard(selected = false)
    }
}