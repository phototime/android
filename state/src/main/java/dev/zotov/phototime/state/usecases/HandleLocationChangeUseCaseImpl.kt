package dev.zotov.phototime.state.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.shared.usecases.*
import dev.zotov.phototime.state.actions.CurrentSunPhaseActions
import dev.zotov.phototime.state.actions.SunPhaseActions
import dev.zotov.phototime.state.blocs.CurrentForecastBloc
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HandleLocationChangeUseCaseImpl : HandleLocationChangeUseCase, KoinComponent {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val fetchForecastUseCase: FetchForecastUseCase by inject()
    private val loadSunPhaseUseCase: LoadSunPhaseUseCase by inject()
    private val currentForecastBloc: CurrentForecastBloc by inject()
    private val sunPhaseActions: SunPhaseActions by inject()
    private val currentSunPhaseActions: CurrentSunPhaseActions by inject()
    private val useLastKnownLocationUseCase: UseLastKnownLocationUseCase by inject()
    private val useCachedForecastUseCase: UseCachedForecastUseCase by inject()

    override fun invoke(location: City, cached: Forecast?) =
        coroutineScope.launch {
            if (cached != null) {
                currentForecastBloc.apply(cached,location)
            }

            val fetchForecastCoroutine = async {
                val forecast = fetchForecastUseCase.execute(location.name)
                currentForecastBloc.applyFetchResult(forecast, location)

                if (forecast.isSuccess) {
                    useCachedForecastUseCase.save(forecast = forecast.getOrThrow())
                }
            }

            val saveLocationCoroutine = async {
                useLastKnownLocationUseCase.save(location)
            }

            val sunPhase = loadSunPhaseUseCase.loadToday(location.latLong, location.timeZone)
            sunPhaseActions.handleGenerated(sunPhase)
            currentSunPhaseActions.start(sunPhase, location.timeZone)

            awaitAll(
                fetchForecastCoroutine,
                saveLocationCoroutine,
            )
        }
}