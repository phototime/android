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
    ) : SunPhaseState() {
        /**
         * finds the phase of the sun corresponding to the [time]
         * @param time by which the phase is searched for
         * @return sun phase if found else null (null also mean night)
         */
        fun get(time: LocalTime): SunPhase? {

            if (
                list.morningBlueHour.start.toLocalTime().isBefore(time)
                && list.morningBlueHour.end.toLocalTime().isAfter(time)
            ) return list.morningBlueHour

            if (
                list.morningGoldenHour.start.toLocalTime().isBefore(time)
                && list.morningGoldenHour.end.toLocalTime().isAfter(time)
            ) return list.morningGoldenHour

            if (
                list.eveningGoldenHour.start.toLocalTime().isBefore(time)
                && list.eveningGoldenHour.end.toLocalTime().isAfter(time)
            ) return list.eveningGoldenHour

            if (
                list.eveningBlueHour.start.toLocalTime().isBefore(time)
                && list.eveningBlueHour.end.toLocalTime().isAfter(time)
            ) return list.eveningBlueHour

            return null
        }


    }

    data class Error(
        /** User friendly formatted error */
        val error: String,
    ) : SunPhaseState()
}

