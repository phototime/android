package dev.zotov.phototime.shared.models

import androidx.annotation.IntRange
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
     * Wind speed in km/h
     * @sample 10.5
     */
    val wind: Float,

    /**
     * Temperature in degrees celsius
     */
    val temp: Int,

    /**
     * concentration of water in the air as a percentage (0% – 100%)
     */
    @IntRange(from = 0, to = 100)
    val humidity: Int,

    ) {
    companion object {
        /** Used to represent city that's not exits or should not be showed */
        val Nothing = CityForecast(
            City.Unknown,
            type = ForecastType.Clear,
            temp = -512,
            wind = 0f,
            humidity = 0,
        )
    }

    fun toForecast(): Forecast = Forecast(
        type = type,
        temp = temp,
        wind = wind,
        humidity = humidity,
        hourly = emptyList()
    )
}

