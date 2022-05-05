package dev.zotov.phototime.state.actions

import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.solarized.SunPhase
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.Store
import dev.zotov.phototime.state.state.CurrentSunPhaseState
import kotlinx.coroutines.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

class CurrentSunPhaseActions(private val store: Store) {

    private val logger = createLogger("CurrentSunPhaseActions")
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private var job: Job? = null

    fun start(list: SunPhaseList) {
        if (job != null && job?.isActive == true) job?.cancel()

        job = coroutineScope.launch {

            val current = list.get(LocalTime.now())
            if (current == null) {
                logger.info { "No sun phase found" }
                store.emitCurrentSunPhase(CurrentSunPhaseState.NoPhase)
                return@launch
            }

            logger.info { "launch timer" }

            var duration = Duration.between(LocalDateTime.now(), getTimeEnd(current))
            val state = CurrentSunPhaseState.Idle(current, duration)
            store.emitCurrentSunPhase(state)

            while (duration.seconds > 0) {
                delay(1000)
                duration = Duration.between(LocalDateTime.now(), getTimeEnd(current))
                store.emitCurrentSunPhase(state.copy(duration = duration))
            }

            delay(1000)
            start(list)
        }
    }

    private fun getTimeEnd(sunPhase: SunPhase): LocalDateTime {
        if (sunPhase is SunPhase.GoldenHour) return sunPhase.end
        if (sunPhase is SunPhase.BlueHour) return sunPhase.end
        if (sunPhase is SunPhase.Day) return sunPhase.end
        throw IllegalArgumentException()
    }
}

fun SunPhaseList.get(time: LocalTime): SunPhase? {
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