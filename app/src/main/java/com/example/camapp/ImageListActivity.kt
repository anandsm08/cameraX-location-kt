package com.example.camapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageListActivity: AppCompatActivity() {
        private lateinit var recyclerView: RecyclerView
        private lateinit var imageAdapter: ImageAdapter
        private val capturedImageUris = mutableListOf<ImageModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_image_list)

                recyclerView = findViewById(R.id.recyclerView)
                recyclerView.layoutManager = GridLayoutManager(this,2)


                imageAdapter= ImageAdapter(capturedImageUris)
                recyclerView.adapter= imageAdapter

        }


}