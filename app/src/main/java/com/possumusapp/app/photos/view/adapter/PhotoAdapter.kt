package com.possumusapp.app.photos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.R
import com.possumusapp.app.photos.model.PhotoModel

class PhotoAdapter(private val list: List<PhotoModel>, private val onClickListener: (PhotoModel)->Unit): RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(layoutInflater.inflate(R.layout.photo_item,parent,false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = list[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int=list.size
    }
