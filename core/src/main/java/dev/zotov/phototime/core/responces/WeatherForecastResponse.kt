package dev.zotov.phototime.core.responces

data class WeatherForecastResponse(
    val current: CurrentWeatherForecast,
    val forecast: WeatherForecast,
)

data class CurrentWeatherForecast(
    val temp_c: Float,
    val wind_mph: Float,
    val humidity: Int,
    val condition: ConditionWeatherForecast,
)

data class ConditionWeatherForecast(
    val text: String,
    val code: Int,
)

data class WeatherForecast(
    val forecastday: List<WeatherDayForecast>,
)

data class WeatherDayForecast(
    val hour: List<WeatherHourForecast>,
)

data class WeatherHourForecast(
    val time_epoch: Long,
    val temp_c: Float,
    val condition: ConditionWeatherForecast,
)