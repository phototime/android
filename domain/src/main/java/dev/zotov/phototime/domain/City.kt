package dev.zotov.phototime.domain

import androidx.annotation.Size

data class City(
    /**
     * the city full name
     */
    val name: String,

    /**
     * Short two-character alphabetic geographical codes used to represent countries and dependent territories
     *
     * Example: RU for Russia, AU for Australia, GB for Great Britain
     */
    @Size(2)
    val countryCode: String,
)