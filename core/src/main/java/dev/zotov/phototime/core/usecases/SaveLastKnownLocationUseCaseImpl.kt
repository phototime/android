package dev.zotov.phototime.core.usecases

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.shared.usecases.SaveLastKnownLocationUseCase
import dev.zotov.phototime.shared.utils.DataStores

class SaveLastKnownLocationUseCaseImpl(private val context: Context) : SaveLastKnownLocationUseCase {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStores.location)

    override suspend fun execute(location: String, latLong: LatLong) {
        val latKey = doublePreferencesKey("lastKnownLocation_lat")
        val lonKey = doublePreferencesKey("lastKnownLocation_lon")
        val locationKey = stringPreferencesKey("lastKnownLocation_location")

        context.dataStore.edit {
            it[latKey] = latLong.latitude
            it[lonKey] = latLong.longitude
            it[locationKey] = location
        }
    }
}