package dev.zotov.phototime.core.usecases

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.shared.createLogger
import dev.zotov.phototime.shared.usecases.UseLastKnownLocationUseCase
import dev.zotov.phototime.shared.utils.DataStores
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.*

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.location)

class UseLastKnownLocationUseCaseImpl(private val context: Context) : UseLastKnownLocationUseCase {

    private val logger = createLogger("UseLastKnownLocationUseCase")

    companion object {
        private val latKey = doublePreferencesKey("lastKnownLocation_lat")
        private val lonKey = doublePreferencesKey("lastKnownLocation_lon")
        private val locationKey = stringPreferencesKey("lastKnownLocation_location")
        private val countryCodeKey = stringPreferencesKey("lastKnownLocation_country_code")
        private val timeZoneKey = stringPreferencesKey("lastKnownLocation_tz")
    }

    override suspend fun getLocation(): City? = context.dataStore.data.map {
        val name = it[locationKey] ?: return@map null
        val countryCode = it[countryCodeKey] ?: return@map null
        val lat = it[latKey] ?: return@map null
        val lon = it[lonKey] ?: return@map null
        val tz = it[timeZoneKey] ?: return@map null

        City(name, countryCode, LatLong(lat, lon), TimeZone.getTimeZone(tz))
    }.firstOrNull()

    override suspend fun save(city: City) {
        try {
            context.dataStore.edit {
                it[latKey] = city.latLong.latitude
                it[lonKey] = city.latLong.longitude
                it[locationKey] = city.name
                it[countryCodeKey] = city.countryCode
                it[timeZoneKey] = city.timeZone.id
            }
            logger.info { "save () finished ${city.timeZone.id}" }
        } catch (e: Throwable) {
            logger.error(e) { "Failed to save last known location" }
        }
    }
}