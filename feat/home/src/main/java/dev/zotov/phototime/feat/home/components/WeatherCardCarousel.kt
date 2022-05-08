package dev.zotov.phototime.feat.home.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.zotov.phototime.state.blocs.CurrentForecastBloc
import dev.zotov.phototime.state.state.ForecastState
import org.koin.androidx.compose.get

@Composable
fun WeatherCardCarousel() {
    val currentForecastBloc = get<CurrentForecastBloc>()
    val forecastState = currentForecastBloc.state.collectAsState().value

    if (forecastState is ForecastState.Idle) {
        val amountOfCards = forecastState.hourly.size
        val selected = forecastState.initialSelectedHourlyCard
        val offset = with(LocalDensity.current) { (selected * 162).dp.toPx() }.toInt()
        val scrollState = rememberScrollState(initial = offset)

        Row(modifier = Modifier.horizontalScroll(scrollState)) {
            for (page in 0 until amountOfCards) {
                val endPadding = when (page) {
                    amountOfCards - 1 -> 25.dp
                    else -> 0.dp
                }

                WeatherCard(
                    selected = page == selected,
                    modifier = Modifier.padding(start = 25.dp, end = endPadding, top = 10.dp),
                    forecast = forecastState.hourly[page]
                )
            }
        }
    }
}