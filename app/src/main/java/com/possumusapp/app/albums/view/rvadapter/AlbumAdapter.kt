package com.possumusapp.app.albums.view.rvadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumModel

class AlbumAdapter(private val list: List<AlbumModel>, private val onClickListener: (AlbumModel)->Unit): RecyclerView.Adapter<AlbumViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(layoutInflater.inflate(R.layout.album_item,parent,false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = list[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int=list.size
    }
