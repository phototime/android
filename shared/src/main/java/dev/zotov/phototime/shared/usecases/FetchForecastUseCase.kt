package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.failures.FetchForecastFailure
import dev.zotov.phototime.shared.models.Forecast

interface FetchForecastUseCase {
    /**
     * @param q what location should be searched
     * @return [Forecast] if success and [FetchForecastFailure] if something goes wrong
     * @see
     */
    suspend fun execute(q: String): Result<Forecast>
}