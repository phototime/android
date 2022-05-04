package dev.zotov.phototime.state.actions

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.ProjectLogger
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.utils.formatDateToUserFriendlyString
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.ForecastState
import java.time.LocalDateTime

class ForecastActions(private val store: Store) {
    private val logger: ProjectLogger = createLogger("ForecastActions")

    fun handleCached(forecast: Forecast, location: City) {
        if (store.forecastState.value is ForecastState.Loading) {
            val newState = ForecastState.Idle(
                location = location,
                date = formatDateToUserFriendlyString(LocalDateTime.now()),
                type = forecast.type,
                temp = forecast.temp,
                wind = forecast.wind.toInt(),
                humidity = forecast.humidity,
                hourly = forecast.hourly,
                initialSelectedHourlyCard = forecast.hourly.indexOfFirst {
                    it.time.dayOfMonth == LocalDateTime.now().dayOfMonth &&
                            it.time.hour == LocalDateTime.now().hour
                }
            )
            logger.info { "emitting new forecast state $newState" }

            store.emitForecast(newState)
        } else {
            logger.info { "Can't cache forecast because forecast is not loading " }
        }
    }

    fun handleFetchResult(forecast: Result<Forecast>, location: City) {
        if (forecast.isFailure) {
            val message = "Failed to fetch forecast" // todo: check network access
            store.emitForecast(ForecastState.Error(message))
        } else {
            val successForecast = forecast.getOrThrow()
            store.emitForecast(ForecastState.Idle(
                location = location,
                date = formatDateToUserFriendlyString(LocalDateTime.now()),
                type = successForecast.type,
                temp = successForecast.temp,
                wind = successForecast.wind.toInt(),
                humidity = successForecast.humidity,
                hourly = successForecast.hourly,
                initialSelectedHourlyCard = successForecast.hourly.indexOfFirst {
                    it.time.dayOfMonth == LocalDateTime.now().dayOfMonth &&
                            it.time.hour == LocalDateTime.now().hour
                }
            ))
        }
    }
}