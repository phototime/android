package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.models.Forecast
import kotlinx.coroutines.Job

interface HandleLocationChangeUseCase {

    operator fun invoke(location: City, cached: Forecast? = null): Job
}