package dev.zotov.phototime.state.state

import dev.zotov.phototime.solarized.SunPhase
import dev.zotov.phototime.solarized.SunPhaseList
import java.time.LocalTime


sealed class SunPhaseState {
    /** Used when data is not ready to be presented */
    object Loading : SunPhaseState()

    /** Used when all data processed */
    data class Idle(
        /** all today sun phases */
        val list: SunPhaseList,
    ) : SunPhaseState()

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ) : SunPhaseState()
}

