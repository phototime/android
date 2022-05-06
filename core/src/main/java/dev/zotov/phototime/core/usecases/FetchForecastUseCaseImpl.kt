package dev.zotov.phototime.core.usecases

import android.util.Log
import dev.zotov.phototime.core.AlgoliaApi
import dev.zotov.phototime.core.TimeApi
import dev.zotov.phototime.core.WeatherApi
import dev.zotov.phototime.core.requests.CityAutoCompleteRequest
import dev.zotov.phototime.core.responces.*
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.shared.failures.FailedToFetchForecast
import dev.zotov.phototime.shared.failures.FailedToSerializeForecast
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.failures.FetchForecastFailure
import dev.zotov.phototime.shared.functions.ForecastTypeFunctions
import dev.zotov.phototime.shared.logger
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.models.HourlyForecast
import kotlinx.coroutines.*
import retrofit2.Response
import java.time.Instant
import java.time.ZoneId
import java.util.*

/**
 * Fetch forecast
 * @see Forecast
 */
internal class FetchForecastUseCaseImpl(
    private val weatherApi: WeatherApi,
    private val algoliaApi: AlgoliaApi,
    private val timeApi: TimeApi,
) : FetchForecastUseCase {
    private val popularCities = listOf(
        City("Bangkok", "TH", LatLong(13.736717, 100.523186), TimeZone.getTimeZone("Asia/Bangkok")),
        City("Paris", "FR", LatLong(48.864716, 2.349014), TimeZone.getTimeZone("Europe/Paris")),
        City("London", "GB", LatLong(51.507359, -0.136439), TimeZone.getTimeZone("Europe/London")),
        City("Dubai", "AE", LatLong(25.276987, 55.296249), TimeZone.getTimeZone("Asia/Dubai")),
        City("Singapore", "SG", LatLong(1.290270, 103.851959), TimeZone.getTimeZone("Asia/Singapore")),
        City("New York", "US", LatLong(40.730610, -73.935242), TimeZone.getTimeZone("America/New_York")),
        City("Istanbul", "TR", LatLong(41.015137, 28.979530), TimeZone.getTimeZone("Asia/Istanbul")),
        City("Tokyo", "JP", LatLong(35.658581, 139.745438), TimeZone.getTimeZone("Asia/Tokyo")),
        City("Moscow", "RU", LatLong(55.751244, 37.618423), TimeZone.getTimeZone("Europe/Moscow")),
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
        val cityResponse = algoliaApi.autocomplete(CityAutoCompleteRequest(q))

        // Success
        if (cityResponse.isSuccessful) {
            val cities = cityResponse.body()?.hits?.toCitiesList()?.distinct()
                ?: return Result.failure(FailedToSerializeForecast())


            return withContext(Dispatchers.IO) {
                val forecast = cities
                    .map {
                        async {
                            val (lat, lon) = it.latLong

                            val deferreds = awaitAll(
                                async { weatherApi.getCurrentForecast(it.name) },
                                async { timeApi.fetchTimeZone(lat, lon) },
                            )

                            @Suppress("UNCHECKED_CAST")
                            val weatherResponse = deferreds[0] as Response<CurrentWeatherForecastResponse>

                            @Suppress("UNCHECKED_CAST")
                            val tzResponse = deferreds[1] as Response<TimezoneResponse>

                            if (weatherResponse.isSuccessful && tzResponse.isSuccessful) {
                                val forecast = weatherResponse.body()
                                val tz = tzResponse.body()

                                if (forecast != null && tz != null) {
                                    logger.info { "${forecast.location.name} is ${TimeZone.getTimeZone(tz.timeZone)}" }

                                    return@async mapCurrentForecastToDomain(
                                        forecast,
                                        it.countryCode,
                                        it.latLong,
                                        TimeZone.getTimeZone(tz.timeZone)
                                    )
                                }
                            }

                            return@async null
                        }
                    }
                    .awaitAll()
                    .filterNotNull()

                return@withContext Result.success(forecast)
            }
        }

        return Result.failure(handleErrorCityRequest(cityResponse))
    }

    override suspend fun ofPopularCities(): Result<List<CityForecast>> {
        try {
            return withContext(Dispatchers.IO) {
                val forecast = mutableListOf<CityForecast>()
                popularCities
                    .map {
                        async {
                            val response = weatherApi.getCurrentForecast(it.name)
                            if (response.isSuccessful) {
                                val model = response.body()
                                if (model != null) {
                                    forecast.add(
                                        mapCurrentForecastToDomain(
                                            model,
                                            it.countryCode,
                                            it.latLong,
                                            it.timeZone,
                                        )
                                    )
                                }
                            }
                        }
                    }
                    .awaitAll()

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

    private fun mapCurrentForecastToDomain(
        body: CurrentWeatherForecastResponse,
        countryCode: String,
        latLong: LatLong,
        timeZone: TimeZone
    ): CityForecast {
        return CityForecast(
            city = City(name = body.location.name, countryCode, latLong, timeZone),
            type = ForecastTypeFunctions.getTypeFromCode(body.current.condition.code),
            temp = body.current.temp_c.toInt(),
            wind = body.current.wind_mph,
            humidity = body.current.humidity
        )
    }

    private fun handleErrorForecastRequest(response: Response<WeatherForecastResponse>): FetchForecastFailure {
        Log.d("FetchForecastUseCase.error", response.errorBody().toString())
        // todo: improve error handling
        return FailedToFetchForecast()
    }

    private fun handleErrorCityRequest(response: Response<CitySearchResponse>): FetchForecastFailure {
        Log.d("FetchForecastUseCase.error", response.errorBody().toString())
        // todo: improve error handling
        return FailedToFetchForecast()
    }
}
