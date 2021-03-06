package dev.zotov.phototime.feat.search.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.feat.search.R
import dev.zotov.phototime.shared.components.WeatherIcon
import dev.zotov.phototime.shared.functions.ForecastTypeFunctions
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.theme.*

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun AnimatedWeatherCityCard(
    modifier: Modifier = Modifier,
    active: Boolean,
    forecast: CityForecast,
    onTap: () -> Unit,
) {
    var state by remember { mutableStateOf<CityForecast?>(null) }

    LaunchedEffect(forecast) {
        state = if (forecast == CityForecast.Nothing) null
        else forecast
    }

    AnimatedContent(targetState = state) {
        if (it != null) WeatherCityCard(modifier = modifier, active = active, it, onTap = onTap)
    }
}


@Composable
fun WeatherCityCard(
    modifier: Modifier = Modifier,
    active: Boolean,
    forecast: CityForecast,
    onTap: () -> Unit
) {
    WeatherCityCardContainer(modifier = modifier, active = active, onTap = onTap) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Temperature(text = "${forecast.temp}??")
                WeatherType(text = ForecastTypeFunctions.getForecastName(forecast.type))
            }

            WeatherIcon(
                id = ForecastTypeFunctions.getCurrentForecastResourceId(forecast.type),
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 3.dp),
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            CityView(text = forecast.city.name)
            Spacer(modifier = Modifier.width(10.dp))
            Country(forecast.city.countryCode)
        }
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
private fun RowScope.CityView(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.White14SpNormal,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = Modifier.weight(1f)
    )
}

@Composable
private fun Country(text: String) {
    Text(text = text, style = MaterialTheme.typography.Grey12spNormal)
}

@Composable
fun WeatherCityCardContainer(
    modifier: Modifier = Modifier,
    active: Boolean,
    onTap: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {

    @Composable
    fun InsideFrame() {
        val backgroundModifier = if (active) Modifier else Modifier.background(TileColor)

        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.24f)
                    .clip(MaterialTheme.shapes.large)
                    .composed { backgroundModifier }
                    .clickable { onTap() }
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
            WeatherCityCard(
                active = true,
                forecast = CityForecast(
                    city = City(
                        "London",
                        "GB",
                        LatLong(51.507359, -0.136439)
                    ),
                    type = ForecastType.Cloudy,
                    temp = 10,
                    wind = 10f,
                    humidity = 52,
                ),
                onTap = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun PreviewNotActive() {
    PhototimeTheme {
        Box(modifier = Modifier.width(155.dp)) {
            WeatherCityCard(
                active = false,
                forecast = CityForecast(
                    city = City(
                        "Moscow",
                        "RU",
                        LatLong(55.751244, 37.618423)
                    ),
                    type = ForecastType.Clear,
                    temp = 10,
                    wind = 10f,
                    humidity = 52,
                ),
                onTap = {},
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = BackgroundPreviewColor)
private fun Preview2Lines() {
    PhototimeTheme {
        Box(modifier = Modifier.width(155.dp)) {
            WeatherCityCard(
                active = false,
                forecast = CityForecast(
                    city = City(
                        "Freixo de Espada ?? Cinta Airport",
                        "PT",
                        LatLong(0.0, 0.0)
                    ),
                    type = ForecastType.Clear,
                    temp = 10,
                    wind = 10f,
                    humidity = 52,
                ),
                onTap = {},
            )
        }
    }
}