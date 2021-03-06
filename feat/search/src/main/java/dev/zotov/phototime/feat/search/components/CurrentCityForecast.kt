package dev.zotov.phototime.feat.search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.feat.search.SearchViewModel
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.blocs.CurrentForecastBloc
import dev.zotov.phototime.state.state.ForecastState
import org.koin.androidx.compose.get

@Composable
fun CurrentCityForecast() {
    // State holders
    val currentForecastBloc = get<CurrentForecastBloc>()
    val searchViewModel = get<SearchViewModel>()

    // State
    val forecast = currentForecastBloc.state.collectAsState().value
    val searchText = searchViewModel.searchText.value

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