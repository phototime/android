package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.models.LatLong

interface GetCityByLatLon {

    operator fun invoke(latLon: LatLong): City
}