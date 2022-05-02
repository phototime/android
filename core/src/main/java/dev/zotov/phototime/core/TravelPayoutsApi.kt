package dev.zotov.phototime.core

import dev.zotov.phototime.core.responces.CitySearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface TravelPayoutsApi {

    /**
     * Fetch matching cities and towns.
     *
     * Visit [Endpoint docs](https://support.travelpayouts.com/hc/en-us/articles/360002322572-Autocomplete-API-for-countries-cities-and-airports) for more information
     * @param location Text for searching (the main parameter)
     */
    @GET("places2?locale=en&types[]=city")
    suspend fun autocomplete(@Query("term") location: String): Response<List<CitySearchResponse>>
}