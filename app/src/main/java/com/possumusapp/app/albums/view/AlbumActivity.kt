package com.possumusapp.app.albums.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.app.albums.model.AlbumCache
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.ActivityAlbumBinding
import com.possumusapp.app.albums.view.rvadapter.AlbumAdapter
import com.possumusapp.app.albums.viewmodel.AlbumViewModel
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.login.view.LoginActivity
import com.possumusapp.app.photos.view.PhotoActivity
import com.possumusapp.commons.NetworkStatusInterface
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {
    @Inject
    lateinit var albumCache: AlbumCache

    @Inject
    lateinit var networkStatusInterface: NetworkStatusInterface

    private lateinit var binding: ActivityAlbumBinding
    val viewModel: AlbumViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromActivity()
        viewModel.isLoading.observe(this, Observer {
            binding.albumProgressBarId.isVisible = it
        })

    }

    override fun onResume() {
        //Cerrar sesion para cambiar de usuario
        binding.closeSesion.setOnClickListener {
            intent = Intent(this@AlbumActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        super.onResume()
    }

    //Funcion que inicia el RecyclerView
    private fun initRecyclerView(list: List<AlbumModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = AlbumAdapter(list) { onItemSelected(it) }
    }

    // Funcion que retorna los albums, dependiendo el usuario que se seleccione en el Login
    private fun getDataFromActivity() {
        val user = intent.extras?.getParcelable<UserModel>("user")
        val connection = networkStatusInterface.isNetworkAvailable(this)
        var url = "/albums"
        if (user == null) {
            binding.viewAllPhotos.visibility = View.VISIBLE
            binding.userWelcomeId.text = "Welcome"
            val cache = albumCache.albums[url] != null
            allPhotosButtom()
            when {
                !cache && connection -> {
                    viewModel.onCreate(url)
                    viewModel.albumModel.observe(this, Observer {
                        albumCache.albums[url] = it
                        initRecyclerView(it)
                    })
                }
                !cache && !connection -> {
                    initRecyclerView(emptyList())
                }
                cache -> {
                    initRecyclerView(albumCache.albums[url]!!)
                }
            }
        } else {
            binding.viewAllPhotos.visibility = View.GONE
            binding.userWelcomeId.text = "Welcome ${user.name}"
            url = "users/${user.id}/albums"
            val cache = albumCache.albums[url] != null
            when {
                !cache && connection -> {
                    viewModel.onCreate(url)
                    viewModel.albumModel.observe(this, Observer {
                        albumCache.albums[url] = it
                        initRecyclerView(it)
                    })
                }
                !cache && !connection -> {
                    initRecyclerView(emptyList())
                }
                cache -> {
                    initRecyclerView(albumCache.albums[url]!!)
                }
            }
        }
    }

    //Boton que devuelve el listado de todas las fotos.
    private fun allPhotosButtom() {
        binding.viewAllPhotos.setOnClickListener {
            intent = Intent(this@AlbumActivity, PhotoActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    //Funcion que escucha cual item del RecyclerView ha sido seleccionado y lo envia al siguiente activity.
    private fun onItemSelected(album: AlbumModel) {
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@AlbumActivity, PhotoActivity::class.java).apply {
            putExtra("album", album)
            putExtra("user", user)
        }
        startActivity(intent)
    }

    override fun onBackPressed() {
        //Boton desactivado
    }
}

