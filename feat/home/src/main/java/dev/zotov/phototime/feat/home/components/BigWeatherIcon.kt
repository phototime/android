package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.components.WeatherIcon
import dev.zotov.phototime.shared.components.WeatherIcons

@Composable
fun BigWeatherIcon(icon: WeatherIcons) {
    WeatherIcon(
        icon = icon,
        modifier = Modifier
            .padding(top = 65.dp)
            .width(235.dp)
    )
}


