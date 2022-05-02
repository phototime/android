package dev.zotov.phototime.core.usecases

import android.util.Log
import dev.zotov.phototime.core.WeatherApi
import dev.zotov.phototime.core.responces.CitySearchResponse
import dev.zotov.phototime.core.responces.CurrentWeatherForecastResponse
import dev.zotov.phototime.core.responces.WeatherForecastResponse
import dev.zotov.phototime.shared.failures.FailedToFetchForecast
import dev.zotov.phototime.shared.failures.FailedToSerializeForecast
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.failures.FetchForecastFailure
import dev.zotov.phototime.shared.functions.ForecastTypeFunctions
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.models.HourlyForecast
import kotlinx.coroutines.*
import retrofit2.Response
import java.time.Instant
import java.time.ZoneId

/**
 * Fetch forecast
 * @see Forecast
 */
internal class FetchForecastUseCaseImpl(private val weatherApi: WeatherApi) : FetchForecastUseCase {
    private val popularCities = listOf(
        "Bangkok",
        "Paris",
        "London",
        "Dubai",
        "Singapore",
        "New York",
        "Istanbul",
        "Tokyo",
        "Moscow"
    )

    override suspend fun execute(q: String): Result<Forecast> {
        val response = weatherApi.getForecast(q)

        // Success -> return WeatherForecastResponse
        if (response.isSuccessful) {
            val model = response.body() ?: return Result.failure(FailedToSerializeForecast())
            return Result.success(mapForecastToDomain(model))
        }

        return Result.failure(handleErrorForecastRequest(response))
    }

    override suspend fun search(q: String): Result<List<CityForecast>> {
        val response = weatherApi.searchCity(q)

        // Success
        if (response.isSuccessful) {
            val cities = response.body() ?: return Result.failure(FailedToSerializeForecast())

            return withContext(Dispatchers.IO) {
                val forecast = mutableListOf<CityForecast>()
                cities
                    .map { async { weatherApi.getCurrentForecast(it.url) } }
                    .awaitAll()
                    .forEach {
                        if (it.isSuccessful) {
                            val model = it.body()
                            if (model != null) {
                                forecast.add(mapCurrentForecastToDomain(model))
                            }
                        }
                    }

                return@withContext Result.success(forecast)
            }
        }

        return Result.failure(handleErrorCityRequest(response))
    }

    override suspend fun ofPopularCities(): Result<List<CityForecast>> {
        try {
            return withContext(Dispatchers.IO) {
                val forecast = mutableListOf<CityForecast>()
                popularCities
                    .map { async { weatherApi.getCurrentForecast(it) } }
                    .awaitAll()
                    .forEach {
                        if (it.isSuccessful) {
                            val model = it.body()
                            if (model != null) {
                                forecast.add(mapCurrentForecastToDomain(model))
                                println(forecast.last())
                            }
                        }
                    }

                return@withContext Result.success(forecast)
            }
        } catch (e: Throwable) {
            return Result.failure(FailedToFetchForecast())
        }
    }

    private fun mapForecastToDomain(body: WeatherForecastResponse): Forecast {
        // build hourly
        val hourly = mutableListOf<HourlyForecast>()

        // loop throw days, then inside loop throw hours, map and add
        for (day in body.forecast.forecastday) {
            for (hour in day.hour) {
                val model = HourlyForecast(
                    type = ForecastTypeFunctions.getTypeFromCode(hour.condition.code),
                    time = Instant.ofEpochSecond(hour.time_epoch).atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                    temp = hour.temp_c.toInt(),
                )
                hourly.add(model)
            }
        }

        return Forecast(
            type = ForecastTypeFunctions.getTypeFromCode(body.current.condition.code),
            temp = body.current.temp_c.toInt(),
            wind = body.current.wind_mph,
            humidity = body.current.humidity,
            hourly = hourly,
        )
    }

    private fun mapCurrentForecastToDomain(body: CurrentWeatherForecastResponse): CityForecast {
        return CityForecast(
            city = body.location.name,
            type = ForecastTypeFunctions.getTypeFromCode(body.current.condition.code),
            temp = body.current.temp_c.toInt(),
            wind = body.current.wind_mph,
        )
    }

    private fun handleErrorForecastRequest(response: Response<WeatherForecastResponse>): FetchForecastFailure {
        Log.d("FetchForecastUseCase.error", response.errorBody().toString())
        // todo: improve error handling
        return FailedToFetchForecast()
    }

    private fun handleErrorCityRequest(response: Response<List<CitySearchResponse>>): FetchForecastFailure {
        Log.d("FetchForecastUseCase.error", response.errorBody().toString())
        // todo: improve error handling
        return FailedToFetchForecast()
    }
}
