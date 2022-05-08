package dev.zotov.phototime.state

import androidx.compose.runtime.mutableStateOf
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.state.state.CitiesForecastState
import dev.zotov.phototime.state.state.CurrentSunPhaseState
import dev.zotov.phototime.state.state.ForecastState
import dev.zotov.phototime.state.state.SunPhaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Store {

    // sun phase state
    private val _sunPhaseState = MutableStateFlow<SunPhaseState>(SunPhaseState.Loading)
    val sunPhaseState get() = _sunPhaseState.asStateFlow()

    fun emitSunPhase(state: SunPhaseState) {
        _sunPhaseState.value = state
    }

    // current sun phase state
    private val _currentSunPhaseState = MutableStateFlow<CurrentSunPhaseState>(CurrentSunPhaseState.Loading)
    val currentSunPhaseState get() = _currentSunPhaseState.asStateFlow()

    fun emitCurrentSunPhase(state: CurrentSunPhaseState) {
        _currentSunPhaseState.value = state
    }

    // cities forecast state
    private val _citiesForecastState = MutableStateFlow<CitiesForecastState>(CitiesForecastState.Loading)
    val citiesForecastState get() = _citiesForecastState.asStateFlow()
    var popularCitiesForecast: List<CityForecast> = mutableListOf()

    fun emitCitiesForecast(state: CitiesForecastState) {
        _citiesForecastState.value = state
    }

    val citiesSearchText = mutableStateOf("")
}

