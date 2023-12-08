package com.lhd.androidbase.common

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

//class Permission(private val activity: ComponentActivity) {
//
//    private val permissionLauncher =
//        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
//            val allPermissionsGranted = permissions.all { it.value }
//            if (allPermissionsGranted) {
//                onGranted()
//            } else {
//                onDenial()
//            }
//        }
//
//    fun reqPermissions(
//        permissions: Array<String>,
//        onGranted: () -> Unit,
//        onDenial: () -> Unit
//    ) {
//        val permissionsToRequest = permissions.filter {
//            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
//        }.toTypedArray()
//        if (permissionsToRequest.isNotEmpty()) {
//            permissionLauncher.launch(permissionsToRequest)
//        } else {
//            // All permissions are already granted
//            onGranted()
//        }
//    }
//
//}


object PermissionManager {
    fun requestPermissions(
        activity: ComponentActivity,
        permissions: Array<String>,
        onPermissionsGranted: () -> Unit,
        onPermissionsDenied: () -> Unit
    ) {
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                val allPermissionsGranted = permissions.all { it.value }
                if (allPermissionsGranted) {
                    // Permissions are granted
                    onPermissionsGranted()
                } else {
                    // Permissions are not granted
                    onPermissionsDenied()
                }
            }.launch(permissionsToRequest)
        } else {
            // All permissions are already granted
            onPermissionsGranted()
        }
    }
}