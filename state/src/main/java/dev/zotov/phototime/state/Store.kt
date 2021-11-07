package dev.zotov.phototime.state

import dev.zotov.phototime.state.state.ForecastState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store {
    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState get() = _forecastState.asStateFlow()

    fun emitForecast(state: ForecastState) {
        _forecastState.value = state
    }
}

