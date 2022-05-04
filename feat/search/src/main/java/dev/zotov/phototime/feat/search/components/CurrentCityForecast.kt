package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.ForecastState
import org.koin.androidx.compose.get

@Composable
fun CurrentCityForecast() {
    val store = get<Store>()
    val forecast = store.forecastState.collectAsState().value
    val searchText by store.citiesSearchText

    AnimatedWeatherCityCard(
        active = true,
        modifier = Modifier.padding(top = 25.dp),
        forecast = if (forecast is ForecastState.Idle && searchText.isBlank()) CityForecast(
            city = forecast.location,
            type = forecast.type,
            temp = forecast.temp,
            wind = forecast.wind.toFloat(),
            humidity = forecast.humidity,
        )
        else CityForecast.Nothing,
        onTap = {},
    )
}