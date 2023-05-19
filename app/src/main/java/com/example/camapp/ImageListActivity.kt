package com.example.camapp


import android.os.Bundle
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class ImageListActivity: AppCompatActivity() {
        private lateinit var recyclerView: RecyclerView
        private lateinit var imageAdapter: ImageAdapter
        private var capturedImageUris = mutableListOf<ImageModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_image_list)

                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

                val imageUris = intent?.getParcelableArrayListExtra<Uri>("imageUris")

                if (imageUris != null) {
                        capturedImageUris.addAll(imageUris.map { uri -> ImageModel(uri, 0.0000000, 0.0000000) })

                }

                imageAdapter= ImageAdapter(capturedImageUris)
                recyclerView.adapter= imageAdapter

                //loadImages()

                }

                /*private fun loadImages(){
                        capturedImageUris.clear()
                        val imageDirectory = getExternalFilesDirs(Environment.DIRECTORY_PICTURES)
                        val files = imageDirectory?.listFiles()?.toList()


                        if (files!= null){

                                for (file in files){
                                        val uri =Uri.fromFile(file)
                                        val imageModel = ImageModel(uri,19.12458295481355, 73.00572787095558)
                                        capturedImageUris.add(imageModel)
                                }
                        }

                }
                */
        }


