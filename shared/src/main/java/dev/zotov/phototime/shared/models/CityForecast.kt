package dev.zotov.phototime.shared.models

import androidx.annotation.Size
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
     * Short two-character alphabetic geographical codes used to represent countries and dependent territories
     *
     * Example: RU for Russia, AU for Australia, GB for Great Britain
     */
    @Size(2)
    val countryCode: String,

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

