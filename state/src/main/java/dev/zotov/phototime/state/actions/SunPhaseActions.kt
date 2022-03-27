package dev.zotov.phototime.state.actions

import dev.zotov.phototime.core.ProjectLogger
import dev.zotov.phototime.core.createLogger
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.SunPhaseState

class SunPhaseActions(private val store: Store) {
    private val logger: ProjectLogger = createLogger("SubPhaseActions")

    fun handleGenerated(sunPhaseList: SunPhaseList) {
        val newState = SunPhaseState.Idle(sunPhaseList)
        logger.info { "emit new state $newState" }
        store.emitSunPhase(newState)
    }

}