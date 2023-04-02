package com.e.porsche.managers

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.e.porsche.App
import com.e.porsche.R

class PermissionManager {
    fun isPermissionLocationGranted(context: Context = App.instance, requestPermission: (() -> Unit)? = null): Boolean{
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }


    fun isPermissionCameraWriteStorageGranted(activity: AppCompatActivity, request: (() ->  Unit)? = null): Boolean {
        return permissionCameraGranted(activity, request).isSuccess && permissionStorageForCameraGranted(activity, request).isSuccess
    }

    fun isPermissionForGalleryGranted(activity: AppCompatActivity, request: (() ->  Unit)? = null): Boolean {
        return permissionStorageForGalleryGranted(activity, request).isSuccess
    }

    private fun permissionStorageForCameraGranted(activity: AppCompatActivity, request: (() ->  Unit)? = null): PermissionResponse {
        return checkPermission(
            activity.getString(R.string.reject_permission),
            activity,
            request,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            activity.getString(R.string.required_write_to_storage_permission),
            PERMISSION_STORAGE_FOR_CAMERA)
    }

    private fun permissionCameraGranted(activity: AppCompatActivity, request: (() -> Unit)? = null): PermissionResponse {
        return checkPermission(
            activity.getString(R.string.reject_permission),
            activity,
            request,
            Manifest.permission.CAMERA,
            activity.getString(R.string.required_camera_permission),
            PERMISSION_CAMERA)
    }



    fun permissionStorageForGalleryGranted(activity: AppCompatActivity, requestPermission: (() -> Unit)? = null): PermissionResponse {
        return checkPermission(
            activity.getString(R.string.reject_permission),
            activity,
            requestPermission,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            activity.getString(R.string.required_write_to_storage_permission),
            PERMISSION_STORAGE_FOR_GALLERY)
    }


    private fun checkPermission(checkText: String, activity: AppCompatActivity, requestPermission: (() -> Unit)? = null,
                                permission: String, infoMessage: String, requestCode: Int): PermissionResponse {
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                if (shouldShowRequestPermission(permission)) {

                    showInfoDialog(
                        checkText,
                        activity,
                        requestPermission,
                        infoMessage,
                        arrayOf(permission),
                        requestCode)
                }
            } else {
                if(requestPermission != null){
                    requestPermission()
                }else{
                    requestPermissions(
                        activity,
                        arrayOf(permission),
                        requestCode)
                }

            }
            return PermissionResponse(false, false)
        }
        return PermissionResponse(true, false)
    }

    private fun shouldShowRequestPermission(permission: String): Boolean {
        return when (permission) {
            Manifest.permission.ACCESS_FINE_LOCATION -> true//!shouldHideRequestPermissionLocation()
            else -> true
        }
    }

//    private fun shouldHideRequestPermissionLocation(): Boolean {
//        return SettingsPrefs().showPermissionLocationDialog
//    }

    private fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int, requestPermission: (() -> Unit)? = null) {
        if(requestPermission != null){
            requestPermission()
        }else{
            ActivityCompat.requestPermissions(activity, permissions, requestCode)
        }
    }

    private fun showInfoDialog(checkText: String, activity: AppCompatActivity, requestPermission: (() -> Unit)? = null, message: String,
                               permissions: Array<String>, requestCode: Int) {

        AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.permission_required))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                requestPermissions(activity, permissions, requestCode, requestPermission)
                dialog?.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog?.dismiss()
            }
            //.setOnCheckListener(checkText) { view, isChecked -> disablePermissionDialog(permissions, isChecked) }
            .show()


    }

//    private fun disablePermissionDialog(permissions: Array<String>, isChecked: Boolean) {
//        Timber.d("Is checked $isChecked")
//        for (permission in permissions) {
//            when (permission) {
//                Manifest.permission.ACCESS_FINE_LOCATION ->
//                    //SettingsPrefs().showPermissionLocationDialog = isChecked
//                else -> {
//                }
//            }
//        }
//    }

    data class PermissionResponse(val isSuccess: Boolean, val hideDialog: Boolean)

    companion object {
        @JvmField
        val PERMISSION_ACCESS_FINE_LOCATION = 101
        @JvmField
        val PERMISSION_STORAGE_FOR_GALLERY = 102
        @JvmField
        val PERMISSION_CAMERA = 103
        @JvmField
        val PERMISSION_STORAGE_FOR_CAMERA = 104

        @JvmField
        val RESULT_LOCATION = 199
    }
}
