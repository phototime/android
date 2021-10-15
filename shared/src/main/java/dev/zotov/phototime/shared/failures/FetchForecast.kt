package dev.zotov.phototime.shared.failures

open class FetchForecastFailure(message: String) :
    Exception("FetchForecastFailure: $message")

class FailedToSerializeForecast: FetchForecastFailure("Failed to weather response from api")
class FailedToFetchForecast: FetchForecastFailure("Failed to fetch forecast :(")