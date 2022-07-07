package com.possumusapp.app.photos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.description.view.DescriptionActivity
import com.possumusapp.app.error.ErrorActivity
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.photos.model.PhotoCache
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.view.adapter.PhotoAdapter
import com.possumusapp.app.photos.viewmodel.PhotoViewModel
import com.possumusapp.commons.NetworkStatusInterface
import com.possumusapp.databinding.ActivityPhotoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    @Inject
    lateinit var photoCache: PhotoCache

    @Inject
    lateinit var networkStatusInterface: NetworkStatusInterface

    @Inject
    lateinit var okHttpClient: OkHttpClient

    private lateinit var binding: ActivityPhotoBinding
    private val viewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromActivity()
        viewModel.isLoading.observe(this, Observer {
            binding.photoProgressBarId.isVisible = it
        })
    }

    // Funcion que retorna las fotos, dependiendo del usuario que se seleccione en el Login y del album que se selecciono.
    private fun getDataFromActivity() {
        val album = intent.extras?.getParcelable<AlbumModel>("album")
        val user = intent.extras?.getParcelable<UserModel>("user")
        var url = "/photos"
        when {
            (album == null && user == null) -> {
                binding.userPhotoWelcomeId.text = "Welcome \nto all Photos"
                checkCacheAndConnection(url)
            }
            (album == null && user != null) -> {
                binding.userPhotoWelcomeId.text = "Welcome ${user.name} \nto all Photos"
                checkCacheAndConnection(url)

            }
            (album != null && user == null) -> {
                url = "albums/${album.id}/photos"
                binding.userPhotoWelcomeId.text = "Welcome to Photos\n from album ${album.id} "
                checkCacheAndConnection(url)
            }
            (album != null && user != null) -> {
                url = "albums/${album.id}/photos"
                binding.userPhotoWelcomeId.text =
                    "Welcome ${user.name}\n to Photos from album ${album.id} "
                checkCacheAndConnection(url)
            }
        }
    }

    //Funcion que verifica la existencia de cache y de conexion a internet
    private fun checkCacheAndConnection(url: String) {
        val connection = networkStatusInterface.isNetworkAvailable(this)
        val cache = photoCache.photos[url] != null
        when {
            !cache && connection -> {
                viewModel.onCreate(url)
                viewModel.photoModel.observe(this, Observer {
                    photoCache.photos[url] = it
                    initRecyclerView(it)
                })
            }
            !cache && !connection -> {
                binding.photoProgressBarId.isVisible = true
            }
            cache -> {
                initRecyclerView(photoCache.photos[url]!!)

            }
        }
    }


    //El boton de back retorna a la actividad de albums, vuelve a enviarle el usuario que habia sido seleccionado en el Login.
    override fun onBackPressed() {
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@PhotoActivity, AlbumActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
        super.finish()
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