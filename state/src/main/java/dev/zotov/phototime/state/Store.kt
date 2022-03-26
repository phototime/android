package dev.zotov.phototime.state

import dev.zotov.phototime.state.state.ForecastState
import dev.zotov.phototime.state.state.SunPhaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store {

    // forecast state
    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState get() = _forecastState.asStateFlow()

    fun emitForecast(state: ForecastState) {
        _forecastState.value = state
    }

    // sun phase state
    private val _sunPhaseState = MutableStateFlow<SunPhaseState>(SunPhaseState.Loading)
    val sunPhaseState get() = _sunPhaseState.asStateFlow()

    fun emitSunPhase(state: SunPhaseState) {
        _sunPhaseState.value = state
    }
}

