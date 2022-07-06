package com.radiant.rpl.testa_kotlin.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@SuppressLint("StaticFieldLeak")
object RunTimePermission: Activity() {

    var MY_PERMISSIONS_REQUEST_CODE=101
    var MY_PERMISSIONS_READ_WRITE_CODE=102
     lateinit var context:Activity

    fun checkPermission(context:Activity): Boolean {
        var permision = false
        this.context=context
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    +ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO))
            != PackageManager.PERMISSION_GRANTED)
            {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_CONTACTS)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.RECORD_AUDIO)
            ) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(
                    "Camera,Acces location,record audio, Read Contacts and Write/Rrad External" +
                            " Storage permissions are required to do the task."
                )
                builder.setTitle("Please grant those permissions")
                builder.setPositiveButton(
                    "OK"
                ) { dialogInterface, i ->
                    ActivityCompat.requestPermissions(
                        context, arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.RECORD_AUDIO
                        ),
                        MY_PERMISSIONS_REQUEST_CODE
                    )
                }
                builder.setNeutralButton("Cancel", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                    context, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.RECORD_AUDIO
                    ),
                    MY_PERMISSIONS_REQUEST_CODE
                )
            }
        }
        else {
            // Do something, when permissions are already granted
            permision = true
          //  Toast.makeText(context, "Permissions already granted", Toast.LENGTH_SHORT).show()
        }
        return permision
    }

    fun checkRaadWrite(context:Activity): Boolean {
        var permision = false
        this.context=context
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                   )
            != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            ) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(
                    "Camera, Read Contacts and Write/Rrad External" +
                            " Storage permissions are required to do the task."
                )
                builder.setTitle("Please grant those permissions")
                builder.setPositiveButton(
                    "OK"
                ) { dialogInterface, i ->
                    ActivityCompat.requestPermissions(
                        context, arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        MY_PERMISSIONS_READ_WRITE_CODE
                    )
                }
                builder.setNeutralButton("Cancel", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                    context, arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,

                    ),
                    MY_PERMISSIONS_READ_WRITE_CODE
                )
            }
        }
        else {
            // Do something, when permissions are already granted
            permision = true
            //  Toast.makeText(context, "Permissions already granted", Toast.LENGTH_SHORT).show()
        }
        return permision
    }
    fun checkAudio(context:Activity): Boolean {
        var permision = false
        this.context=context
        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    )
            != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.RECORD_AUDIO)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            ) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(
                    "Record Audio, Read Contacts and Write/Rrad External" +
                            " Storage permissions are required to do the task."
                )
                builder.setTitle("Please grant those permissions")
                builder.setPositiveButton(
                    "OK"
                ) { dialogInterface, i ->
                    ActivityCompat.requestPermissions(
                        context, arrayOf(
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        MY_PERMISSIONS_READ_WRITE_CODE
                    )
                }
                builder.setNeutralButton("Cancel", null)
                val dialog = builder.create()
                dialog.show()
            } else {
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                    context, arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,

                        ),
                    MY_PERMISSIONS_READ_WRITE_CODE
                )
            }
        }
        else {
            // Do something, when permissions are already granted
            permision = true
            //  Toast.makeText(context, "Permissions already granted", Toast.LENGTH_SHORT).show()
        }
        return permision
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] + grantResults[1] + grantResults[2] + grantResults[3] + grantResults[4]+grantResults[5] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(context, "Permissions granted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permissions denied.", Toast.LENGTH_SHORT).show()
            }
        }
       else if (requestCode == MY_PERMISSIONS_READ_WRITE_CODE) {
            if (grantResults.size > 0 && grantResults[0] + grantResults[1] + grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(context, "Permissions granted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permissions denied.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun checkManagePermissn(context: Context):Boolean
    {
        var permsiion = false
        this.context= context as Activity
        if (ContextCompat.checkSelfPermission(context, Manifest.permission. MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                managePermission(context)
           }
        else {
              permsiion=true
           }
        return permsiion
    }


fun managePermission(context: Context): Boolean {
        var permsiion = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            } else {
                permsiion = true
            }
        } else {
            permsiion = true
        }
        return permsiion
    }


}