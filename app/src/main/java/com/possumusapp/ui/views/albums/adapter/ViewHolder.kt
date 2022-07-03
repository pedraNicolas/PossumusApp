package com.possumusapp.ui.views.albums.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.data.model.Data
import com.possumusapp.databinding.PhotoItemBinding

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding=PhotoItemBinding.bind(view)

    fun render(list: Data, onClickListener: (Data)->Unit){
        binding.idTextView.text=list.title
        itemView.setOnClickListener{onClickListener(list)}
    }
}