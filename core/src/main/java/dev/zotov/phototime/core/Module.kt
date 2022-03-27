package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.*
import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.shared.usecases.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { provideOkHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
    single<FetchForecastUseCase> { FetchForecastUseCaseImpl(get()) }
    single<UseLastKnownLocationUseCase> { UseLastKnownLocationUseCaseImpl(androidContext()) }
    single<GetLocationNameFromLatLon> { GetLocationNameFromLatLonImpl(androidContext()) }
    single<UseCachedForecastUseCase> { UseCachedForecastUseCaseImpl(androidContext()) }
    single<LoadSunPhaseUseCase> { LoadSunPhaseUseCaseImpl() }
    single<UseCachedSunPhasesUseCase> { UseCachedSunPhasesUseCaseImpl(androidContext()) }
}