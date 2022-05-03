package dev.zotov.phototime.core

import dev.zotov.phototime.core.requests.CityAutoCompleteRequest
import dev.zotov.phototime.core.responces.CitySearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AlgoliaApi {

    /**
     * Fetch matching cities and towns.
     *
     * Visit [Endpoint docs](https://community.algolia.com/places/api-clients.html#search) for more information
     */
    @POST("places/query")
    suspend fun autocomplete(@Body request: CityAutoCompleteRequest): Response<CitySearchResponse>
}