package com.possumusapp.app.albums.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.AlbumItemBinding

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = AlbumItemBinding.bind(view)

    fun render(list: AlbumModel, onClickListener: (AlbumModel) -> Unit) {
        binding.idTextView.text = list.title
//        println(list.url)
//        if (list.url == null) {
//            binding.imageViewId.visibility = View.GONE
//        } else {
//            binding.imageViewId.visibility = View.VISIBLE
//            Glide.with(binding.imageViewId.context).load("${list.thumbnailUrl.toString()}")
//                .error(
//                    Glide.with(binding.imageViewId.context).load("${list.thumbnailUrl.toString()}.jpg")
//                        .error(
//                            Glide.with(binding.imageViewId.context)
//                                .load("${list.thumbnailUrl.toString()}.png")
//                                .error(
//                                    Glide.with(binding.imageViewId.context)
//                                        .load("${list.thumbnailUrl.toString()}.bmp")
//                                        .error(
//                                            Glide.with(binding.imageViewId.context)
//                                                .load("${list.thumbnailUrl.toString()}.jpeg")
//                                                .error(
//                                                    Glide.with(binding.imageViewId.context)
//                                                        .load("${list.thumbnailUrl.toString()}.gif")
//                                                )
//                                        )
//                                )
//                        )
//                )
//
//                .into(binding.imageViewId)

//        }

        itemView.setOnClickListener { onClickListener(list) }
    }
}