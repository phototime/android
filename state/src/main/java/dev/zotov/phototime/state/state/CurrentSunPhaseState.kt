package dev.zotov.phototime.state.state

import dev.zotov.phototime.solarized.SunPhase
import java.time.Duration

sealed class CurrentSunPhaseState {
    /** Used when all data processed */
    data class Idle(
        /** the phase of the sun right now */
        val phase: SunPhase,

        /** time left ot end of this sun phase */
        val duration: Duration
    ) : CurrentSunPhaseState()

    /** It's night time or just not loaded yet */
    object NoPhase: CurrentSunPhaseState()

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ) : CurrentSunPhaseState()
}

