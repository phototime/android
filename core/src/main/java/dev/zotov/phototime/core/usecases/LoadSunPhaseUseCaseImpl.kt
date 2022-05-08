package dev.zotov.phototime.core.usecases

import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.failures.GenerateSunPhaseFailure
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.shared.usecases.LoadSunPhaseUseCase
import dev.zotov.phototime.solarized.Solarized
import dev.zotov.phototime.solarized.SunPhaseList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.util.*

class LoadSunPhaseUseCaseImpl : LoadSunPhaseUseCase, KoinComponent {
    private val logger = createLogger("LoadSunPhaseUseCaseImpl")

    override suspend fun loadToday(latLong: LatLong, timeZone: TimeZone): SunPhaseList {
        // get today date
        val today = LocalDateTime.now()

        logger.info { "timezone is $timeZone" }

        // generate sun phases list
        val list = Solarized(latLong.latitude, latLong.longitude, today, timeZone).list

        // handle error
        if (list == null) {
            logger.error {
                "failed to generate sun phases for (${latLong.latitude}, ${latLong.longitude}) at $today"
            }
            throw GenerateSunPhaseFailure()
        }

        logger.info { "Load today sun phases(${latLong.latitude}, ${latLong.longitude}) :\n$list" }

        return list
    }
}