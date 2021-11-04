package dev.zotov.phototime.state

import dev.zotov.phototime.state.actions.ForecastActions
import org.koin.dsl.module

val stateModule = module {
    single { Store() }
    single { ForecastActions(get()) }
}