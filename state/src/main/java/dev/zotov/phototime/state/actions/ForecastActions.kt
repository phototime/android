package dev.zotov.phototime.state.actions

import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.utils.formatDateToUserFriendlyString
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.ForecastState
import java.time.LocalDateTime

class ForecastActions(private val store: Store) {

    fun handleFetchResult(forecast: Result<Forecast>, location: String) {
        if (forecast.isFailure) {
            val message = "Failed to fetch forecast" // todo: check network access
            store.emitForecast(ForecastState.Error(message))
        } else {
            val successForecast = forecast.getOrThrow()
            store.emitForecast(ForecastState.Idle(
                location = location,
                date = formatDateToUserFriendlyString(LocalDateTime.now()),
                type = ForecastType.SunWithCloud,
                temp = successForecast.temp,
                wind = successForecast.wind.toInt(),
                humidity = successForecast.humidity,
                hourly = successForecast.hourly,
                initialSelectedHourlyCard = LocalDateTime.now().hour
            ))
        }
    }
}