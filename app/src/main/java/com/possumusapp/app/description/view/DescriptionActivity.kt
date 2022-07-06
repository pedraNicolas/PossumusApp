package com.possumusapp.app.description.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.view.PhotoActivity
import com.possumusapp.databinding.ActivityDescriptionBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionActivity : AppCompatActivity() {
    lateinit var binding: ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val photo = intent.extras?.getParcelable<PhotoModel>("photo")
        Picasso.get()
            .load(photo?.url)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imageView)
        binding.albumId.text="Album Id: ${photo?.albumId.toString()}"
        binding.id.text="Id: ${photo?.id.toString()}"
        binding.titleId.text="Title: ${photo?.title.toString()}"
    }
    override fun onBackPressed() {
        val album = intent.extras?.getParcelable<AlbumModel>("album")
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@DescriptionActivity, PhotoActivity::class.java).apply {
            putExtra("user", user)
            putExtra("album", album)
        }
        finish()
        startActivity(intent)
        super.onBackPressed()
    }
}