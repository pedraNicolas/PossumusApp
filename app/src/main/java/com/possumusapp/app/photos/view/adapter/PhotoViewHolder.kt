package com.possumusapp.app.photos.view.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.possumusapp.R
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.databinding.PhotoItemBinding
import com.squareup.picasso.Picasso

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = PhotoItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(photo: PhotoModel, onClickListener: (PhotoModel) -> Unit) {
        binding.albumIdTextView.text="AlbumId: ${photo.albumId.toString()}"
        binding.idTextView.text = "Id: ${photo.id.toString()}"
        binding.titleTextView.text="Title: ${photo.title}"
        //Glide.with(binding.imageViewId.context).load(list.thumbnailUrl).placeholder(R.drawable.progress_animation).into(binding.imageViewId)
        Picasso.get()
            .load(photo.thumbnailUrl)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imageViewId)
        itemView.setOnClickListener { onClickListener(photo) }
    }
}