package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.failures.GenerateSunPhaseFailure
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.solarized.Solarized

interface LoadSunPhaseUseCase {
    /**
     * Load list with sun phases from [Solarized] that correspond to today's date
     * @param latLong location of user device
     * @throws GenerateSunPhaseFailure if [Solarized.list] return null
     * @return sun phases list
     */
    suspend fun loadToday(latLong: LatLong): SunPhaseList
}