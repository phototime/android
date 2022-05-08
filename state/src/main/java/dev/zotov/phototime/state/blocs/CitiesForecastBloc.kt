package dev.zotov.phototime.state.blocs

import dev.zotov.bloc.Bloc
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.state.state.CitiesForecastState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CitiesForecastBloc : Bloc<CitiesForecastState>(CitiesForecastState.Loading), KoinComponent {

    // Usecases
    private val fetchForecastUseCase: FetchForecastUseCase by inject()

    // Logger
    private val logger = createLogger("CitiesForecastBloc")

    /**
     * Saving [popularCitiesForecast] in order to display this forecast if the user clear the search
     * field or selects a city. This is a kind of in-RAM cache
     */
    private var popularCitiesForecast: List<CityForecast> = emptyList()

    /**
     * Search cities forecast by [q] query and updates [state] with new forecast
     * @param q query to search. Search will fetch forecast popular cities (and cache it) if [q] is
     * blank.
     */
    fun search(q: String) = coroutineScope.launch {
        logger.info { "search($q) action" }

        // Using popular cities forecast if query is empty
        val forecast = if (q.isBlank()) {
            if (popularCitiesForecast.isEmpty()) fetchForecastUseCase.ofPopularCities()
            else {
                // If there is already fetched forecast for popular cities, just use it and return
                stateFlow.update { CitiesForecastState.Idle(popularCitiesForecast) }
                return@launch
            }
        } else {
            fetchForecastUseCase.search(q)
        }

        if (forecast.isFailure) {
            logger.warn { "failure fetch result: ${forecast.exceptionOrNull()}" }
            val message = "Failed to fetch cities forecast" // todo: check network access
            stateFlow.update { CitiesForecastState.Error(message) }
        } else {
            val citiesForecast = forecast.getOrThrow()

            // caching forecast if it's forecast of popular cities
            if (q.isBlank()) popularCitiesForecast = citiesForecast

            stateFlow.update {
                // Emit NotFound state if no cities was found
                if (citiesForecast.isEmpty()) CitiesForecastState.NotFound
                else CitiesForecastState.Idle(citiesForecast)
            }
        }
    }

}