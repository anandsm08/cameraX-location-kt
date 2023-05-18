package com.example.camapp

import android.Manifest
import android.content.pm.PackageManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.camapp.databinding.ActivityMainBinding
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private var imgCapture: ImageCapture?=null
    private  lateinit var cameraExecutor: ExecutorService
    private val REQ_PERMS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
    var camera:Camera?=null
    private var cameraSelector =CameraSelector.DEFAULT_BACK_CAMERA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //perms
        if (permsgranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(this, REQ_PERMS, 0)
        }

        viewBinding.captureBtn.setOnClickListener{ takePhoto()}

        cameraExecutor =Executors.newSingleThreadExecutor()

    }

    private fun permsgranted(): Boolean{
        for (permission in REQ_PERMS){
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }
    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
            preview?.setSurfaceProvider(viewBinding.cameraView?.surfaceProvider)
            imgCapture = ImageCapture.Builder().build()
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this,cameraSelector,preview,imgCapture).also{camera}

        },ContextCompat.getMainExecutor(this))
    }
    private fun takePhoto(){
        val imgcapture = imgCapture?:return
        //format
        val photoFile =File(externalMediaDirs.firstOrNull(),"CamApp${System.currentTimeMillis()}.jpg")
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imgCapture?.takePicture(output,ContextCompat.getMainExecutor(this),object:ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(applicationContext,"Image Saved Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onError(Exception: ImageCaptureException){
                TODO("Not yet implemented")
            }
        })

    }
}