package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.models.Forecast
import kotlinx.coroutines.flow.Flow

interface UseCachedForecastUseCase {
    /**
     * Save forecast in cache on storage to use it on startup before fetching or when there is no
     * network access now
     *
     * @return Forecast if any saved value,
     */
    suspend fun save(forecast: Forecast)

    /**
     * Get forecast from cache storage to use it on startup before fetching or when there is no
     * network access now
     *
     * @return Forecast if any saved value,
     */
    suspend fun get(): Flow<Forecast?>
}