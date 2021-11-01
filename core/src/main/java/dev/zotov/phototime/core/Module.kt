package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.core.usecases.GetLastKnownLocationUseCaseImpl
import dev.zotov.phototime.core.usecases.GetLocationNameFromLatLonImpl
import dev.zotov.phototime.core.usecases.SaveLastKnownLocationUseCaseImpl
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.usecases.GetLastKnownLocationUseCase
import dev.zotov.phototime.shared.usecases.GetLocationNameFromLatLon
import dev.zotov.phototime.shared.usecases.SaveLastKnownLocationUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { provideOkHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
    single<FetchForecastUseCase> { FetchForecastUseCaseImpl(get()) }
    single<GetLastKnownLocationUseCase> { GetLastKnownLocationUseCaseImpl(androidContext()) }
    single<SaveLastKnownLocationUseCase> { SaveLastKnownLocationUseCaseImpl(androidContext()) }
    single<GetLocationNameFromLatLon> { GetLocationNameFromLatLonImpl(androidContext()) }
}