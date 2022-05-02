package dev.zotov.phototime.core

import dev.zotov.phototime.core.responces.CurrentWeatherForecastResponse
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

    /**
     * Fetch current forecast
     *
     * Visit [Endpoint docs](https://www.weatherapi.com/docs/#apis-realtime) for more information
     * @param location US Zipcode, UK Postcode, Canada Postalcode, IP address, Latitude/Longitude
     * (decimal degree) or city name.
     */
    @GET("current.json?aqi=no")
    suspend fun getCurrentForecast(
        @Query("q") location: String,
        @Query("key") key: String = BuildConfig.WEATHER_API_KEY
    ): Response<CurrentWeatherForecastResponse>
}