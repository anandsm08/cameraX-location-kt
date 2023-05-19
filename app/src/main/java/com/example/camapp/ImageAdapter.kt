package com.example.camapp


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type

class ImageAdapter(private val imageList: MutableList<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): ImageViewHolder{
        val itenView = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)

        return ImageViewHolder(itenView)
    }

    override fun onBindViewHolder(holder:ImageViewHolder,position: Int) {
        val image =imageList[position]
        holder.imageView.setImageURI(image.uri)
    }

    override fun getItemCount(): Int{
        return imageList.size
    }

    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView =itemView.findViewById(R.id.imageView)
    }



}