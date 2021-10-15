package dev.zotov.phototime.shared.models

import androidx.annotation.IntRange

/**
 * Represent current forecast.
 * Includes hourly forecast (2 days)
 */
data class Forecast(
    /**
     * Icon which corresponds to this weather
     */
    val icon: String, // todo: change to enum

    /**
     * Temperature in degrees celsius
     * @sample 25
     */
    val temp: Int,

    /**
     * Wind speed in km/h
     * @sample 10.5
     */
    val wind: Float,

    @IntRange(from = 0, to = 100) val humidity: Int,
    /**
     * Hourly weather forecast (usually 2 days)
     * @see HourlyForecast
     */
    val hourly: List<HourlyForecast>,
)
