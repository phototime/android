package dev.zotov.phototime.state.actions

import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.CitiesForecastState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesForecastActions(
    private val store: Store,
    private val fetchForecastUseCase: FetchForecastUseCase
) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun handleFetchResult(forecast: Result<List<CityForecast>>) {
        if (forecast.isFailure) {
            val message = "Failed to fetch cities forecast" // todo: check network access
            store.emitCitiesForecast(CitiesForecastState.Error(message))
        } else {
            val citiesForecast = forecast.getOrThrow()
            store.emitCitiesForecast(CitiesForecastState.Idle(citiesForecast))
        }
    }

    fun search(q: String) = coroutineScope.launch {
        val cities = if (q.isEmpty()) {
            fetchForecastUseCase.ofPopularCities()
        } else {
            fetchForecastUseCase.search(q)
        }

        handleFetchResult(cities)
    }
}