package dev.zotov.phototime.state.actions

import dev.zotov.phototime.state.Store
import kotlinx.coroutines.flow.collectLatest

class ForecastActions(private val store: Store) {

    fun changeTemp() {
        val state = store.forecastState.value
        store.emitForecast(state.copy(temp = 24))
    }
}