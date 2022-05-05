package dev.zotov.phototime.core

import dev.zotov.phototime.core.responces.TimezoneResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApi {

    @GET("Time/current/coordinate")
    suspend fun fetchTimeZone(
        @Query("latitude") latitude: Double,
        @Query("latitude") longitude: Double
    ): Response<TimezoneResponse>
}