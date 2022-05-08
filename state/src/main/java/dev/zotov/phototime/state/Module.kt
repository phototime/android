package dev.zotov.phototime.state

import dev.zotov.phototime.shared.usecases.HandleLocationChangeUseCase
import dev.zotov.phototime.state.blocs.CitiesForecastBloc
import dev.zotov.phototime.state.blocs.CurrentForecastBloc
import dev.zotov.phototime.state.blocs.CurrentSunPhaseBloc
import dev.zotov.phototime.state.blocs.SunPhasesBloc
import dev.zotov.phototime.state.usecases.HandleLocationChangeUseCaseImpl
import org.koin.dsl.module

val stateModule = module {
    single { CurrentForecastBloc() }
    single { SunPhasesBloc() }
    single { CitiesForecastBloc() }
    single { CurrentSunPhaseBloc() }
    single<HandleLocationChangeUseCase> { HandleLocationChangeUseCaseImpl() }
}