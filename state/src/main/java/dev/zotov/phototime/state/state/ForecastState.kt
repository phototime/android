package dev.zotov.phototime.state.state

import androidx.compose.runtime.Composable
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.models.HourlyForecast
import java.time.LocalDateTime

sealed class ForecastState {
    /** Used when data is not ready to be presented */
    object Loading : ForecastState()

    /** Used when all data processed */
    data class Idle(
        /** User friendly formatted location of this forecast */
        val location: String,

        /** On what user friendly formatted date is this forecast stored */
        val date: String,

        /** Forecast weather type */
        val type: ForecastType,

        /** Forecast temperature in degrees celsius */
        val temp: Int,

        /** Forecast wind in km/h */
        val wind: Int,

        /** Forecast humidity in % */
        val humidity: Int,

        /** Hourly forecast */
        val hourly: List<HourlyForecast>,

        /** Which card should be initially selected */
        val initialSelectedHourlyCard: Int,
    ) : ForecastState()

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ) : ForecastState()
}
