package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.models.LatLong
import kotlinx.coroutines.flow.Flow

interface GetLastKnownLocationUseCase {

    suspend fun getLatLon(): Flow<LatLong?>

    suspend fun getLocationName(): Flow<String?>
}