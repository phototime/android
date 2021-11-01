package dev.zotov.phototime.core.usecases

import android.content.Context
import android.location.Geocoder
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.shared.usecases.GetLocationNameFromLatLon
import java.io.IOException
import java.util.*

class GetLocationNameFromLatLonImpl(private val context: Context): GetLocationNameFromLatLon {

    override fun execute(latLon: LatLong): String {
        val geocoder = Geocoder(context, Locale.getDefault())

        return try {
            val addresses = geocoder.getFromLocation(latLon.latitude, latLon.longitude, 1)
            if (addresses.size == 0) "Nowhere"
            else addresses[0].locality
        } catch (e: IOException) {
            "Nowhere"
        }
    }
}