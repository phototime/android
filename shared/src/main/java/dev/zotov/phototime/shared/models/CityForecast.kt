package dev.zotov.phototime.shared.models

import dev.zotov.phototime.domain.ForecastType

/**
 * Represent forecast for [city].
 */
data class CityForecast(
    /**
     * the city that this [CityForecast] describes
     */
    val city: String,

    /**
     * Type of current weather (sunny, cloudy, rainy etc)
     */
    val type: ForecastType,

    /**
     * Temperature in degrees celsius
     * @sample 25
     */
    val temp: Int,
)

