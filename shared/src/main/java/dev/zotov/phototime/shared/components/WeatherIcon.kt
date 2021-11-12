package dev.zotov.phototime.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.functions.ForecastTypeFunctions

@Composable
fun WeatherIcon(modifier: Modifier = Modifier, type: ForecastType) {
    val painter = painterResource(id = ForecastTypeFunctions.getResourceId(type))

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF090620)
private fun Preview() {
    WeatherIcon(type = ForecastType.HeavyRain, modifier = Modifier
        .width(235.dp)
        .padding(20.dp))
}
