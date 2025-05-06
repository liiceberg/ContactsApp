package ru.liiceberg.presentation.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun PermissionHandler(
    permission: String,
    onPermissionLauncherReady: (launchPermissionRequest: () -> Unit) -> Unit,
    onResult: (PermissionResult) -> Unit
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        val result = if (isGranted) {
            PermissionResult.Granted
        } else {
            val shouldShowRationale = (context as? Activity)?.let {
                ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
            } ?: false

            if (shouldShowRationale) PermissionResult.ShowRationale
            else PermissionResult.PermanentlyDenied
        }

        onResult(result)
    }

    LaunchedEffect(Unit) {
        val currentStatus = ContextCompat.checkSelfPermission(context, permission)
        val result = when {
            currentStatus == PackageManager.PERMISSION_GRANTED -> PermissionResult.Granted
            (context as? Activity)?.shouldShowRequestPermissionRationale(permission) == true ->
                PermissionResult.ShowRationale
            else -> PermissionResult.RequestNeeded
        }

        onResult(result)
    }

    onPermissionLauncherReady {
        launcher.launch(permission)
    }
}

