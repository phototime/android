package dev.zotov.phototime.shared.models

import androidx.annotation.FloatRange

/** user geo location */
data class LatLong(
    @FloatRange(from = 0.0, to = 90.0) val latitude: Double,
    @FloatRange(from = 0.0, to = 180.0) val longitude: Double,
)
