package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.models.LatLong

interface SaveLastKnownLocationUseCase {

    suspend fun execute(location: String, latLong: LatLong)
}