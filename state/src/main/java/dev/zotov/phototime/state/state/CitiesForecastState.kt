package dev.zotov.phototime.state.state

import dev.zotov.phototime.shared.models.CityForecast

sealed class CitiesForecastState {

    /** Used when data is not ready to be presented */
    object Loading : CitiesForecastState()

    /** Used when found cities */
    data class Idle(
        val cities: List<CityForecast>
    ): CitiesForecastState()

    /** No cities found */
    object NotFound: CitiesForecastState()

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ) : CitiesForecastState()
}