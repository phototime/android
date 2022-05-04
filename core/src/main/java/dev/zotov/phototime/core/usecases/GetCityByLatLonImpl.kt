package dev.zotov.phototime.core.usecases

import android.content.Context
import android.location.Geocoder
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.shared.usecases.GetCityByLatLon
import java.io.IOException
import java.util.*

class GetCityByLatLonImpl(private val context: Context) : GetCityByLatLon {

    override fun invoke(latLon: LatLong): City {
        val geocoder = Geocoder(context, Locale.ENGLISH)

        return try {
            val addresses = geocoder.getFromLocation(latLon.latitude, latLon.longitude, 1)
            if (addresses.size == 0) City.Unknown
            else City(
                name = addresses[0].locality,
                countryCode = addresses[0].countryCode,
                latLong = latLon
            )
        } catch (e: IOException) {
            City.Unknown
        }
    }
}