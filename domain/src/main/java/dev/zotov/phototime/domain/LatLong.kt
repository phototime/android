package dev.zotov.phototime.domain

import androidx.annotation.FloatRange

/** user geo location */
data class LatLong(
    @FloatRange(from = -90.0, to = 90.0) val latitude: Double,
    @FloatRange(from = -180.0, to = 180.0) val longitude: Double,
)
