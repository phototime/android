package dev.zotov.phototime.shared.functions

import androidx.annotation.DrawableRes
import dev.zotov.phototime.domain.ForecastType
import dev.zotov.phototime.shared.R
import dev.zotov.phototime.shared.models.Forecast
import java.time.LocalDateTime

object ForecastTypeFunctions {

    @DrawableRes
    fun getCurrentForecastResourceId(type: ForecastType) = getResourceId(type, LocalDateTime.now())

    @DrawableRes
    fun getResourceId(type: ForecastType, time: LocalDateTime): Int {
        val isDay = time.hour in 6..19
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

    fun getTypeFromCode(code: Int): ForecastType = when (code) {
        1000 -> ForecastType.Clear
        1003, 1006 -> ForecastType.Cloudy
        1009, 1171, 1180, 1183, 1186, 1198, 1243 -> ForecastType.Overcast
        1063, 1150, 1153, 1168, 1204, 1207 -> ForecastType.PatchyRainPossible
        1249, 1252, 1255, 1258, 1261, 1264 -> ForecastType.PatchySleetPossible
        1066 -> ForecastType.PatchySnowPossible
        1072 -> ForecastType.PatchyFreezingPossible
        1087 -> ForecastType.ThunderyOutbreaksPossible
        1114, 1147, 1213, 1216, 1219 -> ForecastType.BlowingSnow
        1117, 1113, 1222, 1225, 1237 -> ForecastType.Blizzard
        1135 -> ForecastType.Mist
        1189, 1192, 1195 -> ForecastType.HeavyRain
        1201, 1246 -> ForecastType.ModerateRain
        1279, 1282 -> ForecastType.SnowWithThunder
        else -> ForecastType.Clear // todo: return error
    }
}