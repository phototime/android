package dev.zotov.phototime.core.usecases

import android.util.Log
import dev.zotov.phototime.core.WeatherApi
import dev.zotov.phototime.core.responces.WeatherForecastResponse
import dev.zotov.phototime.shared.failures.FailedToFetchForecast
import dev.zotov.phototime.shared.failures.FailedToSerializeForecast
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.failures.FetchForecastFailure
import dev.zotov.phototime.shared.models.HourlyForecast
import retrofit2.Response
import java.time.Instant
import java.time.ZoneId

/**
 * Fetch forecast
 * @see Forecast
 */
internal class FetchForecastUseCaseImpl(private val weatherApi: WeatherApi) : FetchForecastUseCase {
    override suspend fun execute(q: String): Result<Forecast> {
        val response = weatherApi.getForecast(q)

        // Success -> return WeatherForecastResponse
        if (response.isSuccessful) {
            val model = response.body() ?: return Result.failure(FailedToSerializeForecast())
            return Result.success(mapForecastToDomain(model))
        }

        return Result.failure(handleErrorRequest(response))
    }

    private fun mapForecastToDomain(body: WeatherForecastResponse): Forecast {
        // build hourly
        val hourly = mutableListOf<HourlyForecast>()

        // loop throw days, then inside loop throw hours, map and add
        for (day in body.forecast.forecastday) {
            for (hour in day.hour) {
                val model = HourlyForecast(
                    icon = hour.condition.text, // todo
                    time = Instant.ofEpochSecond(hour.time_epoch).atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                    temp = hour.temp_c.toInt(),
                )
                hourly.add(model)
            }
        }

        return Forecast(
            icon = body.current.condition.text, // todo
            temp = body.current.temp_c.toInt(),
            wind = body.current.wind_mph,
            humidity = body.current.humidity,
            hourly = hourly,
        )
    }

    private fun handleErrorRequest(response: Response<WeatherForecastResponse>): FetchForecastFailure {
        Log.d("FetchForecastUseCase.error", response.errorBody().toString())
        // todo: improve error handling
        return FailedToFetchForecast()
    }
}
