package com.possumusapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.data.model.Albums
import com.possumusapp.databinding.ItemBinding

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding=ItemBinding.bind(view)

    fun render(list: Albums,onClickListener: (Albums)->Unit){
        binding.idTextView.text=list.title
        itemView.setOnClickListener{onClickListener(list)}
    }
}