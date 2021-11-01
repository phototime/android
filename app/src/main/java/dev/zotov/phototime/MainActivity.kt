package dev.zotov.phototime

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.Task
import com.vmadalin.easypermissions.EasyPermissions
import dev.zotov.phototime.shared.Routes
import dev.zotov.phototime.shared.theme.PhototimeTheme
import dev.zotov.phototime.shared.usecases.FetchForecastUseCase
import org.koin.android.ext.android.inject
import com.google.android.gms.common.api.ApiException

import android.content.IntentSender
import android.content.IntentSender.SendIntentException
import android.os.Looper

import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*


class MainActivity : ComponentActivity(), EasyPermissions.PermissionCallbacks {

    private val fetchForecastUseCase: FetchForecastUseCase by inject()

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 10002
        const val REQUEST_CHECK_SETTINGS = 10001
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if (hasLocationPermission()) {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = 60000
            locationRequest.fastestInterval = 5000

            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            builder.setAlwaysShow(true)

            val result: Task<LocationSettingsResponse> =
                LocationServices.getSettingsClient(this@MainActivity)
                    .checkLocationSettings(builder.build())

            result.addOnCompleteListener { task ->
                try {
                    task.getResult(ApiException::class.java)
                    val fusedLocationProviderClient =
                        LocationServices.getFusedLocationProviderClient(this@MainActivity)

                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        object : LocationCallback() {
                            override fun onLocationResult(p0: LocationResult) {
                                super.onLocationResult(p0)
                                Log.d("location", p0.toString())
                                fusedLocationProviderClient.removeLocationUpdates(this)
                            }
                        },
                        Looper.getMainLooper(),
                    )
                } catch (e: ApiException) {
                    Log.e("location", e.toString())
                    when (e.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                            val resolvableApiException = e as ResolvableApiException
                            resolvableApiException.startResolutionForResult(
                                this@MainActivity,
                                REQUEST_CHECK_SETTINGS
                            )
                            val fusedLocationProviderClient =
                                LocationServices.getFusedLocationProviderClient(this@MainActivity)

                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest,
                                object : LocationCallback() {
                                    override fun onLocationResult(p0: LocationResult) {
                                        super.onLocationResult(p0)
                                        Log.d("location", p0.toString())
                                        fusedLocationProviderClient.removeLocationUpdates(this)
                                    }
                                },
                                Looper.getMainLooper(),
                            )
                        } catch (ex: SendIntentException) {
                            ex.printStackTrace()
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        }
                    }
                }
            }
        } else {
            requestLocationPermission()
        }


        setContent {
            PhototimeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.Main.route) {
                    composable(Routes.Main.route) { MainFeature(mainNavController = navController) }
                }
            }
        }
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(
            this@MainActivity,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without Location Permission.",
            PERMISSION_LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        TODO("Not yet implemented")
    }

    @SuppressLint("MissingPermission")
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.d("onPermissionsGranted", requestCode.toString())

        if (requestCode == PERMISSION_LOCATION_REQUEST_CODE) {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.interval = 60000
            locationRequest.fastestInterval = 5000

            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            builder.setAlwaysShow(true)

            val result: Task<LocationSettingsResponse> =
                LocationServices.getSettingsClient(this@MainActivity)
                    .checkLocationSettings(builder.build())

            result.addOnCompleteListener { task ->
                try {
                    task.getResult(ApiException::class.java)
                    val fusedLocationProviderClient =
                        LocationServices.getFusedLocationProviderClient(this@MainActivity)

                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        object : LocationCallback() {
                            override fun onLocationResult(p0: LocationResult) {
                                super.onLocationResult(p0)
                                Log.d("location2", p0.toString())
                                fusedLocationProviderClient.removeLocationUpdates(this)
                            }
                        },
                        Looper.getMainLooper(),
                    )
                } catch (e: ApiException) {
                    Log.e("location", e.toString())
                    when (e.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                            val resolvableApiException = e as ResolvableApiException
                            resolvableApiException.startResolutionForResult(
                                this@MainActivity,
                                REQUEST_CHECK_SETTINGS
                            )
                            val fusedLocationProviderClient =
                                LocationServices.getFusedLocationProviderClient(this@MainActivity)

                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest,
                                object : LocationCallback() {
                                    override fun onLocationResult(p0: LocationResult) {
                                        super.onLocationResult(p0)
                                        Log.d("location", p0.toString())
                                        fusedLocationProviderClient.removeLocationUpdates(this)
                                    }
                                },
                                Looper.getMainLooper(),
                            )
                        } catch (ex: SendIntentException) {
                            ex.printStackTrace()
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        }
                    }
                }
            }


        }
    }

    private fun checkGPSPermissions(context: Context): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }
}