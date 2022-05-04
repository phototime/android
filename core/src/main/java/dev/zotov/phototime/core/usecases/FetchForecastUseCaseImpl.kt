package dev.zotov.phototime.core.usecases

import android.util.Log
import dev.zotov.phototime.core.AlgoliaApi
import dev.zotov.phototime.core.WeatherApi
import dev.zotov.phototime.core.requests.CityAutoCompleteRequest
import dev.zotov.phototime.core.responces.CitySearchResponse
import dev.zotov.phototime.core.responces.CurrentWeatherForecastResponse
import dev.zotov.phototime.core.responces.WeatherForecastResponse
import dev.zotov.phototime.core.responces.toCitiesList
import dev.zotov.phototime.domain.City
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
internal class FetchForecastUseCaseImpl(
    private val weatherApi: WeatherApi,
    private val algoliaApi: AlgoliaApi
) : FetchForecastUseCase {
    private val popularCities = listOf(
        City("Bangkok", "TH"),
        City("Paris", "FR"),
        City("London", "GB"),
        City("Dubai", "AE"),
        City("Singapore", "SG"),
        City("New York", "US"),
        City("Istanbul", "TR"),
        City("Tokyo", "JP"),
        City("Moscow", "RU"),
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
        val response = algoliaApi.autocomplete(CityAutoCompleteRequest(q))

        // Success
        if (response.isSuccessful) {
            val cities = response.body()?.hits?.toCitiesList()?.distinct()
                ?: return Result.failure(FailedToSerializeForecast())

            return withContext(Dispatchers.IO) {
                val forecast = mutableListOf<CityForecast>()
                cities
                    .map {
                        async {
                            val res =
                                weatherApi.getCurrentForecast(it.name)
                            if (res.isSuccessful) {
                                val model = res.body()
                                if (model != null) {
                                    forecast.add(mapCurrentForecastToDomain(model, it.countryCode))
                                }
                            }
                        }
                    }
                    .awaitAll()

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
                    .map {
                        async {
                            val response = weatherApi.getCurrentForecast(it.name)
                            if (response.isSuccessful) {
                                val model = response.body()
                                if (model != null) {
                                    forecast.add(mapCurrentForecastToDomain(model, it.countryCode))
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
    ): CityForecast {
        return CityForecast(
            city = body.location.name,
            type = ForecastTypeFunctions.getTypeFromCode(body.current.condition.code),
            temp = body.current.temp_c.toInt(),
            countryCode = countryCode
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
