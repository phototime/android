package dev.zotov.phototime

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vmadalin.easypermissions.EasyPermissions
import dev.zotov.phototime.shared.Routes
import dev.zotov.phototime.shared.theme.PhototimeTheme
import com.google.android.gms.common.api.ApiException

import android.content.IntentSender.SendIntentException
import android.os.Looper

import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import dev.zotov.phototime.domain.City
import dev.zotov.phototime.shared.logger
import dev.zotov.phototime.shared.models.CityForecast
import dev.zotov.phototime.shared.models.Forecast
import dev.zotov.phototime.domain.LatLong
import dev.zotov.phototime.shared.usecases.*
import dev.zotov.phototime.solarized.SunPhaseList
import dev.zotov.phototime.state.actions.CitiesForecastActions
import dev.zotov.phototime.state.actions.ForecastActions
import dev.zotov.phototime.state.actions.SunPhaseActions
import io.sentry.Sentry
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.firstOrNull
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity(), EasyPermissions.PermissionCallbacks {

    companion object {
        const val PERMISSION_LOCATION_REQUEST_CODE = 10002
        const val REQUEST_CHECK_SETTINGS = 10001
    }

    private val getCityByLatLong: GetCityByLatLon by inject()
    private val useCachedForecastUseCase: UseCachedForecastUseCase by inject()
    private val useCachedSunPhasesUseCase: UseCachedSunPhasesUseCase by inject()
    private val useLastKnownLocationUseCase: UseLastKnownLocationUseCase by inject()
    private val fetchForecastUseCase: FetchForecastUseCase by inject()
    private val handleLocationChangeUseCase: HandleLocationChangeUseCase by inject()
    private val forecastActions: ForecastActions by inject()
    private val sunPhaseActions: SunPhaseActions by inject()
    private val citiesForecastActions: CitiesForecastActions by inject()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        if (BuildConfig.DEBUG) {
            Sentry.close()
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        CoroutineScope(Dispatchers.IO).launch {
            val deferreds = awaitAll(
                async { useCachedForecastUseCase.get().firstOrNull() },
                async { useLastKnownLocationUseCase.getLocation() },
                async { useCachedSunPhasesUseCase.get().firstOrNull() },
                async { fetchForecastUseCase.ofPopularCities() }
            )

            val forecast = deferreds[0] as Forecast?
            val location = deferreds[1] as City?
            val sunPhasesList = deferreds[2] as SunPhaseList?

            @Suppress("UNCHECKED_CAST")
            val popularCitiesForecast = deferreds[3] as Result<List<CityForecast>>?

            if (forecast != null && location != null) forecastActions.handleCached(
                forecast = forecast,
                location = location,
            )

            if (location == null) {
                if (hasLocationPermission()) getAllPermissions()
                else requestLocationPermission()
            }

            if (sunPhasesList != null) sunPhaseActions.handleCached(sunPhasesList)

            if (popularCitiesForecast != null) citiesForecastActions.handleFetchResult(
                popularCitiesForecast,
                isPopularCities = true,
            )
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

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == PERMISSION_LOCATION_REQUEST_CODE) {
            getAllPermissions()
        }
    }

    private fun getAllPermissions() {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 60000
            fastestInterval = 5000
        }

        val builder = LocationSettingsRequest.Builder().apply {
            addLocationRequest(locationRequest)
            setAlwaysShow(true)
        }

        val result = LocationServices.getSettingsClient(this@MainActivity)
            .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->

            try {
                task.getResult(ApiException::class.java)
                getAndSaveLocation(locationRequest)
            } catch (e: ApiException) {
                Log.e("location", e.toString())
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                        val resolvableApiException = e as ResolvableApiException
                        resolvableApiException.startResolutionForResult(
                            this@MainActivity,
                            REQUEST_CHECK_SETTINGS
                        )
                        getAndSaveLocation(locationRequest)
                    } catch (ex: SendIntentException) {
                        ex.printStackTrace()
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getAndSaveLocation(locationRequest: LocationRequest) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this@MainActivity)

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)

                    logger.info { "LocationResult is $p0" }

                    if (p0.locations.size > 0) {
                        val location = p0.locations[0]
                        val latLong = LatLong(location.latitude, location.longitude)
                        val city = getCityByLatLong(latLong)

                        logger.info { "Location is $latLong" }

                        handleLocationChangeUseCase(city)
                    }

                    fusedLocationProviderClient.removeLocationUpdates(this)
                }
            },
            Looper.getMainLooper(),
        )
    }

}