package com.example.camapp

import  android.Manifest

object constants {

    const val FILE_FORMAT = "yy-MM-dd-HH-mm-ss-SSS"
    const val  REQ_CODE_PERMS = 121
    val REQ_PERMS = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION) // Use android.manifest.permission.<permission-name>

}