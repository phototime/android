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
import dev.zotov.phototime.shared.usecases.UseLastKnownLocationUseCase
import dev.zotov.phototime.shared.utils.DataStores
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.location)

class UseLastKnownLocationUseCaseImpl(private val context: Context) : UseLastKnownLocationUseCase {

    companion object {
        private val latKey = doublePreferencesKey("lastKnownLocation_lat")
        private val lonKey = doublePreferencesKey("lastKnownLocation_lon")
        private val locationKey = stringPreferencesKey("lastKnownLocation_location")
        private val countryCodeKey = stringPreferencesKey("lastKnownLocation_country_code")
    }

    override suspend fun getLocation(): City? = context.dataStore.data.map {
        val name = it[locationKey] ?: return@map null
        val countryCode = it[countryCodeKey] ?: return@map null
        val lat = it[latKey] ?: return@map null
        val lon = it[latKey] ?: return@map null

        City(name, countryCode, LatLong(lat, lon))
    }.firstOrNull()

    override suspend fun save(city: City) {
        try {
            context.dataStore.edit {
                it[latKey] = city.latLong.latitude
                it[lonKey] = city.latLong.longitude
                it[locationKey] = city.name
                it[countryCodeKey] = city.countryCode
            }
            println("save () finished")
        } catch (e: Throwable) {
            Log.e("SaveLastKnownLocationUseCaseImpl", e.toString())
        }
    }
}