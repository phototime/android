package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.core.usecases.UseLastKnownLocationUseCaseImpl
import dev.zotov.phototime.core.usecases.GetLocationNameFromLatLonImpl
import dev.zotov.phototime.core.usecases.UseCachedForecastUseCaseImpl
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.usecases.UseLastKnownLocationUseCase
import dev.zotov.phototime.shared.usecases.GetLocationNameFromLatLon
import dev.zotov.phototime.shared.usecases.UseCachedForecastUseCase
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
}