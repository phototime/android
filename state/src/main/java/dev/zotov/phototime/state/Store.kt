package dev.zotov.phototime.state

import androidx.compose.runtime.mutableStateOf
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.state.CitiesForecastState
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

    var popularCitiesForecast: List<CityForecast> = mutableListOf()

    // cities forecast state
    private val _citiesForecastState = MutableStateFlow<CitiesForecastState>(CitiesForecastState.Loading)
    val citiesForecastState get() = _citiesForecastState.asStateFlow()

    fun emitCitiesForecast(state: CitiesForecastState) {
        _citiesForecastState.value = state
    }

    val citiesSearchText = mutableStateOf("")
}

