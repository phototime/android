package dev.zotov.phototime.shared.usecases

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong

interface GetCityByLatLon {

    operator fun invoke(latLon: LatLong): City
}