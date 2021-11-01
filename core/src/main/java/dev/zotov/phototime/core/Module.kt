package dev.zotov.phototime.core

import dev.zotov.phototime.core.usecases.FetchForecastUseCaseImpl
import dev.zotov.phototime.core.usecases.GetLastKnowLocationUseCaseImpl
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import dev.zotov.phototime.shared.usecases.GetLastKnowLocationUseCase
import org.koin.core.scope.get
import org.koin.dsl.module

val coreModule = module {
    single { provideOkHttpClient() }
    single { provideWeatherApi(get()) }
    single { provideRetrofit(get()) }
    single<FetchForecastUseCase> { FetchForecastUseCaseImpl(get()) }
    single<GetLastKnowLocationUseCase> { GetLastKnowLocationUseCaseImpl() }
}