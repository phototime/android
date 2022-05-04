package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong

interface UseLastKnownLocationUseCase {

    suspend fun getLocation(): City?

    suspend fun save(city: City)
}