package dev.zotov.phototime.shared.models

import androidx.annotation.Size
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.ForecastType

/**
 * Represent forecast for [city].
 */
data class CityForecast(
    /**
     * the [City] that this [CityForecast] describes
     */
    val city: City,

    /**
     * Type of current weather (sunny, cloudy, rainy etc)
     */
    val type: ForecastType,

    /**
     * Temperature in degrees celsius
     *
     * Example: 25
     */
    val temp: Int,
)

