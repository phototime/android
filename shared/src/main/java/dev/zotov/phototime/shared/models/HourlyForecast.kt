package dev.zotov.phototime.shared.models

import dev.zotov.phototime.domain.ForecastType
import java.time.LocalDateTime

/**
 * Represent weather forecast at a specific hour
 */
data class HourlyForecast(
    /**
     * Type of current weather (sunny, cloudy, rainy etc)
     */
    val type: ForecastType,

    /**
     * The time that this hourly forecast corresponds to. Contains date and hour, minutes are always 0
     * @sample 2021-01-01 14:00
     */
    val time: LocalDateTime,

    /**
     * Temperature in degrees celsius
     * @sample 25
     */
    val temp: Int,
)
