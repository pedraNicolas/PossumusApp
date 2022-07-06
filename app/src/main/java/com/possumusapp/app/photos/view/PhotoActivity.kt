package com.possumusapp.app.photos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.description.view.DescriptionActivity
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.view.adapter.PhotoAdapter
import com.possumusapp.app.photos.viewmodel.PhotoViewModel
import com.possumusapp.databinding.ActivityPhotoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding
    private val viewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromActivity()
        viewModel.photoModel.observe(this, Observer {
            initRecyclerView(it)
        })
        viewModel.isLoading.observe(this, Observer {
            binding.photoProgressBarId.isVisible = it
        })
    }

    // Funcion que retorna las fotos, dependiendo del usuario que se seleccione en el Login y del album que se selecciono.
    private fun getDataFromActivity() {
        val album = intent.extras?.getParcelable<AlbumModel>("album")
        val user = intent.extras?.getParcelable<UserModel>("user")
        when {
            (album == null && user == null) -> {
                binding.userPhotoWelcomeId.text = "Welcome \nto all Photos"
                viewModel.onCreate("/photos")
            }
            (album == null && user != null) -> {
                binding.userPhotoWelcomeId.text = "Welcome ${user?.name} \nto all Photos"
                viewModel.onCreate("/photos")
            }
            (album != null && user == null) -> {
                binding.userPhotoWelcomeId.text = "Welcome to Photos\n from album ${album.id} "
                viewModel.onCreate("albums/${album.id}/photos")
            }
            (album != null && user != null) -> {
                binding.userPhotoWelcomeId.text =
                    "Welcome ${user?.name}\n to Photos from album ${album.id} "
                viewModel.onCreate("albums/${album.id}/photos")
            }
        }
    }

    //El boton de back retorna a la actividad de albums, vuelve a enviarle el usuario que habia sido seleccionado en el Login.
    override fun onBackPressed() {
        val album = intent.extras?.getParcelable<AlbumModel>("album")
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@PhotoActivity, AlbumActivity::class.java).apply {
            putExtra("user", user)
        }
        finish()
        startActivity(intent)
        super.onBackPressed()
    }

    //Funcion que inicia el Recycler View
    private fun initRecyclerView(list: List<PhotoModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = PhotoAdapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(photo: PhotoModel) {
        val album = intent.extras?.getParcelable<AlbumModel>("album")
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@PhotoActivity, DescriptionActivity::class.java).apply {
            putExtra("photo", photo)
            putExtra("album", album)
            putExtra("user", user)
        }
        startActivity(intent)

    }


}