package dev.zotov.phototime.state.actions

import dev.zotov.phototime.shared.ProjectLogger
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.SunPhaseState
import org.koin.core.component.KoinComponent

class SunPhaseActions(private val store: Store): KoinComponent {
    private val logger: ProjectLogger = createLogger("SubPhaseActions")

    fun handleGenerated(sunPhaseList: SunPhaseList) {
        val newState = SunPhaseState.Idle(sunPhaseList)
        logger.info { "emit new state $newState" }
        store.emitSunPhase(newState)
    }

    fun handleCached(sunPhaseList: SunPhaseList) {
        if (store.sunPhaseState.value !is SunPhaseState.Idle) {
            val newState = SunPhaseState.Idle(sunPhaseList)
            logger.info { "emit new state $newState" }
            store.emitSunPhase(newState)
        }
    }
}