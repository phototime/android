package dev.zotov.phototime.core.responces

import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong
import java.util.*

data class CitySearchResponse(
    val hits: List<CitySearchObject>
);

data class CitySearchObject(
    val locale_names: CitySearchNames,
    val country_code: String,
    val _geoloc: CityGeoLocation
) {
    fun toCity(): City = City(
        name = locale_names.default!!.first(),
        countryCode = country_code.uppercase(Locale.getDefault()),
        latLong = LatLong(
            latitude = _geoloc.lat,
            longitude = _geoloc.lng,
        )
    )
}

fun List<CitySearchObject>.toCitiesList(): List<City> = this.mapNotNull {
    if (it.locale_names.default != null && it.locale_names.default.isNotEmpty()) it.toCity()
    else null
}

data class CitySearchNames(
    val default: List<String>?,
)

data class CityGeoLocation(
    val lat: Double,
    val lng: Double,
)