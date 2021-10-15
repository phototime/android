package dev.zotov.phototime.core

import dev.zotov.phototime.core.responces.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApi {

    @GET("forecast.json?days=2&aqi=no&alerts=no")
    suspend fun getForecast(
        @Query("q") location: String,
        @Query("key") key: String = BuildConfig.WEATHER_API_KEY
    ): Response<WeatherForecastResponse>
}