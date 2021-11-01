package dev.zotov.phototime.shared.usecases

import android.content.Context
import android.location.LocationManager

interface GetLastKnowLocationUseCase {

    /**
     * Get last know device location
     * @param context should be passed because use case have no access to activity context
     * @return User friendly location name (example: San Francisco)
     */
    suspend fun execute(context: Context): String?
}