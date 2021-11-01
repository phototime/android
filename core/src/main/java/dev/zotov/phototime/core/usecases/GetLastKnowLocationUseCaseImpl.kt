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


// Todo
class GetLastKnowLocationUseCaseImpl : GetLastKnowLocationUseCase {

    override suspend fun execute(context: Context): String {
        return ""
    }


}