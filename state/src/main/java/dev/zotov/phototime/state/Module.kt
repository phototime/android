package dev.zotov.phototime.state

import dev.zotov.phototime.shared.usecases.HandleLocationChangeUseCase
import dev.zotov.phototime.state.actions.CitiesForecastActions
import dev.zotov.phototime.state.actions.ForecastActions
import dev.zotov.phototime.state.actions.SunPhaseActions
import dev.zotov.phototime.state.usecases.HandleLocationChangeUseCaseImpl
import org.koin.dsl.module

val stateModule = module {
    single { Store() }
    single { ForecastActions(get()) }
    single { SunPhaseActions(get()) }
    single { CitiesForecastActions(get(), get()) }
    single<HandleLocationChangeUseCase> { HandleLocationChangeUseCaseImpl() }
}