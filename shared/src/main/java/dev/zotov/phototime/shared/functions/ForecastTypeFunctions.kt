package dev.zotov.phototime.shared.functions

import androidx.annotation.DrawableRes
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.R
import java.time.LocalDateTime

object ForecastTypeFunctions {

    @DrawableRes
    fun getResourceId(type: ForecastType): Int {
        val isDay = LocalDateTime.now().hour in 6..19
        return if (isDay) getDayResourceId(type)
        else getNightResourceId(type)
    }

    private fun getDayResourceId(type: ForecastType): Int = when (type) {
        ForecastType.Clear -> R.drawable.sun
        ForecastType.Cloudy -> R.drawable.sun_cloud
        ForecastType.Overcast -> R.drawable.cloud_rain
        ForecastType.Mist -> R.drawable.sun_cloud_wind
        ForecastType.PatchyRainPossible -> R.drawable.cloud
        ForecastType.PatchySnowPossible -> R.drawable.cloud
        ForecastType.PatchySleetPossible -> R.drawable.cloud
        ForecastType.PatchyFreezingPossible -> R.drawable.cloud_snow_rain
        ForecastType.ThunderyOutbreaksPossible -> R.drawable.light
        ForecastType.BlowingSnow -> R.drawable.cloud_snow
        ForecastType.Blizzard -> R.drawable.snow
        ForecastType.HeavyRain -> R.drawable.cloud_rain_light
        ForecastType.ModerateRain -> R.drawable.sun_cloud_rain_light
        ForecastType.SnowWithThunder -> R.drawable.cloud_snow_light
    }

    @DrawableRes
    private fun getNightResourceId(type: ForecastType): Int = when (type) {
        ForecastType.Clear -> R.drawable.moon
        ForecastType.Cloudy -> R.drawable.cloud
        ForecastType.Overcast -> R.drawable.moon_cloud_rain
        ForecastType.Mist -> R.drawable.cloud
        ForecastType.PatchyRainPossible -> R.drawable.cloud
        ForecastType.PatchySnowPossible -> R.drawable.cloud
        ForecastType.PatchySleetPossible -> R.drawable.cloud
        ForecastType.PatchyFreezingPossible -> R.drawable.cloud
        ForecastType.ThunderyOutbreaksPossible -> R.drawable.light
        ForecastType.BlowingSnow -> R.drawable.moon_cloud_snow
        ForecastType.Blizzard -> R.drawable.snow
        ForecastType.HeavyRain -> R.drawable.cloud_rain_light
        ForecastType.ModerateRain -> R.drawable.moon_cloud_rain
        ForecastType.SnowWithThunder -> R.drawable.cloud_snow_light
    }

}