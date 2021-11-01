package dev.zotov.phototime.core.usecases

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import arrow.core.Either
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import dev.zotov.phototime.shared.failures.GetLocationFailure
import dev.zotov.phototime.shared.models.LatLong
import dev.zotov.phototime.shared.usecases.GetLastKnowLocationUseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class GetLastKnowLocationUseCaseImpl : GetLastKnowLocationUseCase {

    override suspend fun execute(context: Context): String? {
        val coordinates = getCoordinates(context)
        println(coordinates)
        return ""
    }

    @SuppressLint("MissingPermission")
    private suspend fun getCoordinates(context: Context): Either<GetLocationFailure, LatLong> =
        suspendCoroutine { task ->
            // Check GPS permission. If denied, can't get latitude and longitude.
            // So, need to return failure
            Log.d("getCoordinates", "checking gps..")
            if (!checkGPSPermissions(context)) {
                Log.d("getCoordinates", "no gps permission")
                requestPermissions(context)
                task.resume(Either.Left(GetLocationFailure.PermissionDenied))
            } else {
                val locationRequest = LocationRequest.create()
                locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

                val result: Task<LocationSettingsResponse> =
                    LocationServices.getSettingsClient(context as Activity)
                        .checkLocationSettings(builder.build())

                result.addOnCompleteListener { resultTask ->
                    try {
                        val response = resultTask.getResult(ApiException::class.java)
                        // All location settings are satisfied. The client can initialize location
                        // requests here.

                        val fusedLocationClient =
                            LocationServices.getFusedLocationProviderClient(context)

                        fusedLocationClient.lastLocation.addOnCompleteListener { locTask ->
                            if (locTask.result == null) {
                                task.resume(Either.Left(GetLocationFailure.PermissionDenied))
                            } else {
                                val latLong = LatLong(
                                    latitude = locTask.result.latitude,
                                    longitude = locTask.result.longitude,
                                )
                                task.resume(Either.Right(latLong))
                            }
                        }

                        fusedLocationClient.lastLocation.addOnFailureListener { exception ->
                            println(exception)
                        }
                    } catch (exception: ApiException) {
                        when (exception.statusCode) {
                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                try {
                                    // Cast to a resolvable exception.
                                    val resolvable: ResolvableApiException =
                                        exception as ResolvableApiException
                                    // Show the dialog by calling startResolutionForResult(),
                                    // and check the result in onActivityResult().
                                    resolvable.startResolutionForResult(context, 1)
                                } catch (e: IntentSender.SendIntentException) {
                                    // Ignore the error.
                                } catch (e: ClassCastException) {
                                    // Ignore, should be an impossible error.
                                }
                            }
                            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                                // Location settings are not satisfied. But could be fixed by showing the
                                // user a dialog.

                                // Location settings are not satisfied. However, we have no way to fix the
                                // settings so we won't show the dialog.
                            }
                        }
                        task.resume(Either.Left(GetLocationFailure.PermissionDenied))
                    }
                }


            }
        }

    private suspend fun showLocationPrompt(context: Context): Boolean = suspendCoroutine { task ->

    }

    /**
     * @return True if granted and false if denied
     */
    private fun checkGPSPermissions(context: Context): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    /**
     * @return True if enabled and false if disabled
     */
    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = getSystemService(context, LocationManager::class.java) ?: return false
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    /** method to request for permissions */
    private fun requestPermissions(context: Context) {
        Log.d("requestPermissions", "Starting");
        try {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        } catch (e: Error) {
            Log.d("requestPermissions", e.toString())
        }
    }
}