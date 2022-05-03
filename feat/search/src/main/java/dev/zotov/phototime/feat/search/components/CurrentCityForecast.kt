package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.ForecastState
import org.koin.androidx.compose.get

@Composable
fun CurrentCityForecast() {
    val store = get<Store>()

    when (val forecast = store.forecastState.collectAsState().value) {
        is ForecastState.Idle -> WeatherCityCard(
            active = true,
            modifier = Modifier.padding(top = 25.dp),
            forecast = CityForecast(
                city = forecast.location,
                type = forecast.type,
                temp = forecast.temp,
                countryCode = "RU", // todo
            )
        )
        else -> Unit
    }
}