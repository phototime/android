package dev.zotov.phototime.core.usecases

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.shared.usecases.GetLastKnownLocationUseCase
import dev.zotov.phototime.shared.utils.DataStores
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLastKnownLocationUseCaseImpl(private val context: Context) : GetLastKnownLocationUseCase {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.location)

    companion object {
        private val latKey = doublePreferencesKey("lastKnownLocation_lat")
        private val lonKey = doublePreferencesKey("lastKnownLocation_lon")
        private val locationKey = stringPreferencesKey("lastKnownLocation_location")
    }

    override suspend fun getLatLon(): Flow<LatLong?> = context.dataStore.data.map {
        val lat = it[latKey]
        val lon = it[lonKey]

        if (lat == null || lon == null) null
        else {
            LatLong(
                latitude = lat,
                longitude = lon,
            )
        }
    }

    override suspend fun getLocationName(): Flow<String?> = context.dataStore.data.map {
        it[locationKey]
    }
}