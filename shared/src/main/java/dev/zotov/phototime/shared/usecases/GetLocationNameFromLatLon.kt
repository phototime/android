package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.shared.models.LatLong

interface GetLocationNameFromLatLon {

    fun execute(latLon: LatLong): String
}