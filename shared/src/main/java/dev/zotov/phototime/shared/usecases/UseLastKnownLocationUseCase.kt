package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.models.LatLong
import kotlinx.coroutines.flow.Flow

interface UseLastKnownLocationUseCase {

    suspend fun getLatLon(): Flow<LatLong?>

    suspend fun getLocationName(): Flow<String?>

    suspend fun save(location: String, latLong: LatLong)
}