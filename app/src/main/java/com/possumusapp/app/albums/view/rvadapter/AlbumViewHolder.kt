package com.possumusapp.app.albums.view.rvadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.AlbumItemBinding

class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = AlbumItemBinding.bind(view)

    fun render(list: AlbumModel, onClickListener: (AlbumModel) -> Unit) {
        binding.userIdTextView.text=list.userId.toString()
        binding.idTextView.text = list.id.toString()
        binding.titleTextView.text=list.title

        itemView.setOnClickListener { onClickListener(list) }
    }
}