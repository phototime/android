package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.models.LatLong

interface UseLastKnownLocationUseCase {

    suspend fun getLatLon(): LatLong?

    suspend fun getLocation(): City?

    suspend fun save(city: City, latLong: LatLong)
}