package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.feat.search.R
import dev.zotov.phototime.shared.components.WeatherIcon
import dev.zotov.phototime.shared.components.WeatherIcons
import dev.zotov.phototime.shared.theme.*

@Composable
fun WeatherCityCard(modifier: Modifier = Modifier, active: Boolean) {
    WeatherCityCardContainer(modifier = modifier, active = active) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Temperature(text = "32°")
                WeatherType(text = "Cloudy")
            }

            WeatherIcon(
                icon = WeatherIcons.SunWithCloud,
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 3.dp)
            )
        }

        City(text = "San Fransisco")
    }
}

@Composable
private fun Temperature(text: String) {
    Text(text = text, style = MaterialTheme.typography.White24spSemiBold)
}

@Composable
private fun WeatherType(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.White13spNormal.copy(color = White.copy(alpha = 0.8f))
    )
}

@Composable
private fun City(text: String) {
    Text(text = text, style = MaterialTheme.typography.White14SpNormal)
}

@Composable
fun WeatherCityCardContainer(
    modifier: Modifier = Modifier,
    active: Boolean,
    content: @Composable ColumnScope.() -> Unit
) {

    @Composable
    fun BoxScope.InsideFrame() {
        val backgroundModifier = if (active) Modifier else Modifier.background(TileColor)

        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.24f)
                    .clip(MaterialTheme.shapes.large)
                    .composed { backgroundModifier }
                    .clickable() { }
                    .padding(start = 20.dp, end = 15.dp, top = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                content()
            }
        }

    }

    if (active) {
        val painter = painterResource(id = R.drawable.active_city_weather_card_background)
        Box(modifier = modifier) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )
            InsideFrame()
        }
    } else {
        Box(modifier = modifier) {
            InsideFrame()
        }
    }

}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewActive() {
    PhototimeTheme {
        Box(modifier = Modifier.width(155.dp)) {
            WeatherCityCard(active = true)

        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewNotActive() {
    PhototimeTheme {
        Box(modifier = Modifier.width(155.dp)) {
            WeatherCityCard(active = false)
        }
    }
}