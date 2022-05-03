package dev.zotov.phototime.core.requests

/**
 * @param query Text for searching (the main parameter)
 */
data class CityAutoCompleteRequest(
    val query: String,
) {
    val type = listOf("city", "townhall")
    val hitsPerPage = 10
}
