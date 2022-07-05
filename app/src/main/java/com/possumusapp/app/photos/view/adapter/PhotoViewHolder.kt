package com.possumusapp.app.photos.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.databinding.PhotoItemBinding
import com.squareup.picasso.Picasso

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = PhotoItemBinding.bind(view)

    fun render(list: PhotoModel, onClickListener: (PhotoModel) -> Unit) {
        binding.albumIdTextView.text=list.albumId.toString()
        binding.idTextView.text = list.id.toString()
        binding.titleTextView.text=list.title

        Picasso.get().load(list.thumbnailUrl).into(binding.imageViewId)
        itemView.setOnClickListener { onClickListener(list) }
    }
}