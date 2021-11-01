package dev.zotov.phototime.shared.failures

sealed class GetLocationFailure(message: String) :
    Exception("GetLocationFailure: $message") {

    object PermissionDenied :
        GetLocationFailure("The application does not have access to geolocation. Please grant permissions in the settings")
}

