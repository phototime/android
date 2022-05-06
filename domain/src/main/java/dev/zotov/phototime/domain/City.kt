package dev.zotov.phototime.domain

import androidx.annotation.Size
import java.util.*

data class City(
    /** the city full name */
    val name: String,

    /**
     * Short two-character alphabetic geographical codes used to represent countries and dependent territories
     *
     * Example: RU for Russia, AU for Australia, GB for Great Britain
     */
    @Size(2)
    val countryCode: String,


    /** Geographical coordinates of the [City] */
    val latLong: LatLong,

    /** Timezone in which this city is located */
    val timeZone: TimeZone = TimeZone.getDefault()
) {
    companion object {
        val Unknown = City("Unknown", "--", LatLong(0.0, 0.0), TimeZone.getDefault())
    }
}