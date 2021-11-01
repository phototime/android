package dev.zotov.phototime.feat.home

import dev.zotov.phototime.shared.components.WeatherIcons
import dev.zotov.phototime.shared.models.HourlyForecast
import dev.zotov.phototime.solarized.SunPhaseList

sealed class HomeState {
    /** Used when data is not ready to be presented */
    object Loading : HomeState()

    /** Used when all data processed */
    data class Idle(
        /** User friendly formatted location */
        val location: String,

        /** User friendly formatted date */
        val date: String,

        /** Selected forecast weather type represented in WeatherIcons */
        val forecastIcon: WeatherIcons,

        /** Selected forecast temperature in degrees celsius` */
        val temp: Int,

        /** Selected forecast wind in km/h */
        val wind: Int,

        /** Selected forecast humidity in % */
        val humidity: Int,

        /** List of sun phases like golden hour, sunrise ... */
        val sunPhases: SunPhaseList,

        /** Hourly forecast */
        val hourly: List<HourlyForecast>
    ): HomeState()

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ): HomeState()
}
