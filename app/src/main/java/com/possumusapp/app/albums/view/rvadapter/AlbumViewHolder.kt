package com.possumusapp.app.albums.view.rvadapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.AlbumItemBinding

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = AlbumItemBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(list: AlbumModel, onClickListener: (AlbumModel) -> Unit) {
        binding.userIdTextView.text="User Id: ${list.userId.toString()}"
        binding.idTextView.text = "Id: ${list.id.toString()}"
        binding.titleTextView.text="Title: ${list.title}"
        itemView.setOnClickListener { onClickListener(list) }
    }
}