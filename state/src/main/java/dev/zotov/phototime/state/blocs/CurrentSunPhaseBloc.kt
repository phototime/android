package dev.zotov.phototime.state.blocs

import dev.zotov.bloc.Bloc
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.solarized.SunPhase
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.state.CurrentSunPhaseState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

class CurrentSunPhaseBloc: Bloc<CurrentSunPhaseState>(CurrentSunPhaseState.NoPhase) {
    // Logger
    private val logger = createLogger("CurrentSunPhaseBloc")

    /** sun phase timer job */
    private var job: Job? = null

    /** Start timer. Emits time left to end of current sun phase every second */
    fun start(list: SunPhaseList, timeZone: TimeZone) {
        // have to replace currently running timer with new one
        if (job?.isActive == true) job?.cancel()

        job = coroutineScope.launch {

            // current sun phase
            val current = list.get(ZonedDateTime.now(timeZone.toZoneId()))

            if (current == null) {
                logger.info { "No sun phase found" }
                stateFlow.update { CurrentSunPhaseState.NoPhase }
                return@launch
            }

            logger.info { "launch timer" }

            var duration = Duration.between(
                ZonedDateTime.now(timeZone.toZoneId()).toLocalDateTime(),
                current.timeEnd
            )

            stateFlow.update { CurrentSunPhaseState.Idle(current, duration) }

            while (duration.seconds > 0) {
                delay(1000)
                duration = Duration.between(
                    ZonedDateTime.now(timeZone.toZoneId()).toLocalDateTime(),
                    current.timeEnd
                )
                stateFlow.update { CurrentSunPhaseState.Idle(current, duration) }
            }

            delay(1000)
            start(list, timeZone)
        }
    }

}

private val SunPhase.timeEnd: LocalDateTime get() {
    if (this is SunPhase.GoldenHour) return this.end
    if (this is SunPhase.BlueHour) return this.end
    if (this is SunPhase.Day) return this.end
    throw IllegalArgumentException()
}

private fun SunPhaseList.get(date: ZonedDateTime): SunPhase? {
    val time = date.toLocalTime()
    if (
        this.morningBlueHour.start.toLocalTime().isBefore(time)
        && this.morningBlueHour.end.toLocalTime().isAfter(time)
    ) return this.morningBlueHour

    if (
        this.morningGoldenHour.start.toLocalTime().isBefore(time)
        && this.morningGoldenHour.end.toLocalTime().isAfter(time)
    ) return this.morningGoldenHour

    if (
        this.day.start.toLocalTime().isBefore(time)
        && this.day.end.toLocalTime().isAfter(time)
    ) return this.day

    if (
        this.eveningGoldenHour.start.toLocalTime().isBefore(time)
        && this.eveningGoldenHour.end.toLocalTime().isAfter(time)
    ) return this.eveningGoldenHour

    if (
        this.eveningBlueHour.start.toLocalTime().isBefore(time)
        && this.eveningBlueHour.end.toLocalTime().isAfter(time)
    ) return this.eveningBlueHour

    return null
}