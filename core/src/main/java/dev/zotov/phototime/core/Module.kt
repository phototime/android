package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.*
import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.shared.usecases.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { provideOkHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideAlgoliaApi(get()) }
    single { provideTimeApi(get()) }
    single<FetchForecastUseCase> { FetchForecastUseCaseImpl(get(), get()) }
    single<UseLastKnownLocationUseCase> { UseLastKnownLocationUseCaseImpl(androidContext()) }
    single<GetCityByLatLon> { GetCityByLatLonImpl(androidContext()) }
    single<UseCachedForecastUseCase> { UseCachedForecastUseCaseImpl(androidContext()) }
    single<LoadSunPhaseUseCase> { LoadSunPhaseUseCaseImpl() }
    single<UseCachedSunPhasesUseCase> { UseCachedSunPhasesUseCaseImpl(androidContext()) }
}