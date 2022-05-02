package dev.zotov.phototime.state.actions

import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.CitiesForecastState

class CitiesForecastActions(private val store: Store) {
    fun handleFetchResult(forecast: Result<List<CityForecast>>) {
        if (forecast.isFailure) {
            val message = "Failed to fetch cities forecast" // todo: check network access
            store.emitCitiesForecast(CitiesForecastState.Error(message))
        } else {
            val citiesForecast = forecast.getOrThrow()
            store.emitCitiesForecast(CitiesForecastState.Idle(citiesForecast))
        }
    }
}