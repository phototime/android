package dev.zotov.phototime.state.blocs

import dev.zotov.bloc.Bloc
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.state.SunPhaseState
import kotlinx.coroutines.flow.update

class SunPhasesBloc : Bloc<SunPhaseState>(SunPhaseState.Loading) {
    // Logger
    private val logger = createLogger("SunPhasesBloc")

    /** Updates [state] with [sunPhaseList] */
    fun apply(sunPhaseList: SunPhaseList) {
        logger.info { "apply($sunPhaseList) action" }
        stateFlow.update { SunPhaseState.Idle(sunPhaseList) }
    }
}