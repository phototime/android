package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.failures.FetchForecastFailure
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.models.Forecast

interface FetchForecastUseCase {
    /**
     * @param q what location should be searched
     * @return [Forecast] if success and [FetchForecastFailure] if something goes wrong
     */
    suspend fun execute(q: String): Result<Forecast>

    /**
     * @param q what location should be searched
     * @return list of [Forecast] if success and[FetchForecastFailure] if something goes wrong
     */
    suspend fun search(q: String): Result<List<CityForecast>>

    /**
     * @return list of [Forecast] if success and[FetchForecastFailure] if something goes wrong
     */
    suspend fun ofPopularCities(): Result<List<CityForecast>>
}