package dev.zotov.phototime.core.usecases

import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.failures.GenerateSunPhaseFailure
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.shared.usecases.LoadSunPhaseUseCase
import dev.zotov.phototime.shared.usecases.UseCachedForecastUseCase
import dev.zotov.phototime.shared.usecases.UseCachedSunPhasesUseCase
import dev.zotov.phototime.solarized.Solarized
import dev.zotov.phototime.solarized.SunPhaseList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime

class LoadSunPhaseUseCaseImpl : LoadSunPhaseUseCase, KoinComponent {
    private val logger = createLogger("LoadSunPhaseUseCaseImpl")

    private val useCachedSunPhasesUseCase: UseCachedSunPhasesUseCase by inject()

    override suspend fun loadToday(latLong: LatLong): SunPhaseList {
        // get today date
        val today = LocalDateTime.now()

        // generate sun phases list
        val list = Solarized(latLong.latitude, latLong.longitude, today).list

        // handle error
        if (list == null) {
            logger.error {
                "failed to generate sun phases for ($latLong.latitude, $latLong.longitude) at $today"
            }
            throw GenerateSunPhaseFailure()
        }

        useCachedSunPhasesUseCase.save(list)

        logger.info { "Load today sun phases:\n$list" }

        return list
    }
}