package dev.zotov.phototime.state.blocs

import dev.zotov.bloc.Bloc
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.models.indexOfCurrentTime
import dev.zotov.phototime.shared.utils.formatDateToUserFriendlyString
import dev.zotov.phototime.state.state.ForecastState
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime

class CurrentForecastBloc : Bloc<ForecastState>(ForecastState.Loading) {

    // Logger
    private val logger = createLogger("CurrentForecastBloc")

    /** Update [state] with cached [forecast] and [location] */
    fun apply(forecast: Forecast, location: City) {
        logger.info { "apply($forecast, $location) action" }
        stateFlow.update {
            ForecastState.Idle(
                location = location,
                date = formatDateToUserFriendlyString(LocalDateTime.now()),
                type = forecast.type,
                temp = forecast.temp,
                wind = forecast.wind.toInt(),
                humidity = forecast.humidity,
                hourly = forecast.hourly,
                initialSelectedHourlyCard = forecast.hourly.indexOfCurrentTime(location.timeZone)
            )
        }
    }

    /** Update [state] with network fetch result [forecastResult] and [location] */
    fun applyFetchResult(forecastResult: Result<Forecast>, location: City) {
        logger.info { "applyFetchResult($forecastResult, $location) action" }

        // Failed request
        if (forecastResult.isFailure) {
            logger.warn { "applyFetchResult: failure fetch result: ${forecastResult.exceptionOrNull()}" }
            // todo: check network access
            val message = "Failed to fetch forecast"
            stateFlow.update { ForecastState.Error(message) }
        } else {
            val forecast = forecastResult.getOrThrow()
            apply(forecast, location)
        }
    }
}