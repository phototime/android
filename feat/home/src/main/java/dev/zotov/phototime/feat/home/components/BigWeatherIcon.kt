package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.components.WeatherIcon

@Composable
fun BigWeatherIcon(type: ForecastType) {
    WeatherIcon(
        type = type,
        modifier = Modifier
            .padding(top = 65.dp)
            .width(235.dp)
    )
}


