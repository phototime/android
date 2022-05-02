package dev.zotov.phototime.state

import dev.zotov.phototime.state.actions.CitiesForecastActions
import dev.zotov.phototime.state.actions.ForecastActions
import dev.zotov.phototime.state.actions.SunPhaseActions
import org.koin.dsl.module

val stateModule = module {
    single { Store() }
    single { ForecastActions(get()) }
    single { SunPhaseActions(get()) }
    single { CitiesForecastActions(get()) }
}