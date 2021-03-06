package dev.zotov.phototime.feat.search

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.zotov.phototime.feat.search.components.*
import dev.zotov.phototime.shared.components.Headline
import dev.zotov.phototime.shared.components.Subtitle
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.usecases.HandleLocationChangeUseCase
import dev.zotov.phototime.state.blocs.CitiesForecastBloc
import dev.zotov.phototime.state.blocs.CurrentForecastBloc
import dev.zotov.phototime.state.state.CitiesForecastState
import dev.zotov.phototime.state.state.ForecastState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun SearchScreen(
    @Suppress("unused") navController: NavHostController,
    scrollState: ScrollState
) {
    // State holders
    val currentForecastBloc = get<CurrentForecastBloc>()
    val searchViewModel = get<SearchViewModel>()
    val citiesForecastBloc = get<CitiesForecastBloc>()

    // Usecases
    val handleLocationChangeUseCase = get<HandleLocationChangeUseCase>()

    // State
    val forecast = currentForecastBloc.state.collectAsState().value
    val searchText = searchViewModel.searchText.value
    val state = citiesForecastBloc.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headline(text = "Pick Location")
        Subtitle(
            text = "Find the area or city that you want to know the perfect photo shoot time and weather",
            modifier = Modifier.width(250.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        SearchTextField()

        val columnModifier = Modifier.weight(1f)

        when (state) {
            is CitiesForecastState.Loading -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 25.dp, end = 25.dp)
                ) {
                    Column(modifier = columnModifier) {
                        for (i in 0..4) LoadingWeatherCityCard()
                    }

                    Spacer(modifier = Modifier.width(30.dp))

                    Column(modifier = columnModifier.padding(top = 30.dp)) {
                        for (i in 0..4) LoadingWeatherCityCard()
                    }
                }
            }
            is CitiesForecastState.NotFound -> NotFoundMessage()
            is CitiesForecastState.Idle -> {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 25.dp, end = 25.dp)
                ) {

                    val isCurrentCityShown = forecast is ForecastState.Idle && searchText.isBlank()
                    val firstColumn =
                        state.cities.filterIndexed { index, _ -> index % 2 == if (isCurrentCityShown) 1 else 0 }
                    val secondColumn =
                        state.cities.filterIndexed { index, _ -> index % 2 == if (isCurrentCityShown) 0 else 1 }

                    fun onTap(cityForecast: CityForecast) = CoroutineScope(Dispatchers.IO).launch {
                        handleLocationChangeUseCase(cityForecast.city, cityForecast.toForecast())
                        searchViewModel.search("")
                        citiesForecastBloc.search("")
                    }

                    Column(modifier = columnModifier) {
                        CurrentCityForecast()

                        firstColumn.forEach {
                            AnimatedWeatherCityCard(
                                active = false,
                                modifier = Modifier.padding(top = 25.dp),
                                forecast = it,
                                onTap = { onTap(it) },
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(30.dp))

                    Column(modifier = columnModifier.padding(top = 30.dp)) {
                        secondColumn.forEach {
                            AnimatedWeatherCityCard(
                                active = false,
                                modifier = Modifier.padding(top = 25.dp),
                                forecast = it,
                                onTap = { onTap(it) },
                            )
                        }
                    }
                }
            }
            is CitiesForecastState.Error -> TODO()
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}