package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.solarized.SunPhaseList
import kotlinx.coroutines.flow.Flow

interface UseCachedSunPhasesUseCase {
    /**
     * Save sun phases in cache on storage to use it on startup before fetching or when there is no
     * network access now
     */
    suspend fun save(sunPhaseList: SunPhaseList)

    /**
     * Get sun phases from cache storage to use it on startup before fetching or when there is no
     * network access now
     *
     * @return SunPhaseList if any saved value,
     */
    suspend fun get(): Flow<SunPhaseList?>
}