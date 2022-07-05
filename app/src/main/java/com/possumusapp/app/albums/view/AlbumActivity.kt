package com.possumusapp.app.albums.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.R
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.ActivityAlbumBinding
import com.possumusapp.app.albums.view.rvadapter.AlbumAdapter
import com.possumusapp.app.albums.viewmodel.AlbumViewModel
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.login.view.LoginActivity
import com.possumusapp.app.photos.view.PhotoActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumBinding
    private val viewModel: AlbumViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFromActivity()
        viewModel.albumModel.observe(this, Observer {
            initRecyclerView(it)
        })
        closeSesionButtom()
    }


    override fun onBackPressed() {
        //Boton desactivado
    }

    // Funcion que retorna los albums, dependiendo el usuario que se seleccione en el Login
    private fun getDataFromActivity() {
        val user = intent.extras?.getParcelable<UserModel>("user")
        if(user==null){
            binding.viewAllPhotos.visibility = View.VISIBLE
            allPhotosButtom()
            binding.userWelcomeId.text = "Welcome"
            viewModel.fetchAlbumData("/albums")
        }else{
            binding.viewAllPhotos.visibility = View.GONE
            binding.userWelcomeId.text = "Welcome ${user.name}"
            viewModel.fetchAlbumData("users/${user.id}/albums")
        }
    }
    //Boton que devuelve el listado de todas las fotos siempre.
    private fun allPhotosButtom(){
        binding.viewAllPhotos.setOnClickListener {
            intent = Intent(this@AlbumActivity,PhotoActivity::class.java)
            finish()
            startActivity(intent)
        }
    }

    //Funcion que inicia el RecyclerView
    private fun initRecyclerView(list: List<AlbumModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = AlbumAdapter(list) { onItemSelected(it) }
    }
    //Funcion que escucha cual item del RecyclerView ha sido seleccionado y lo envia al siguiente activity.
    private fun onItemSelected(album: AlbumModel) {
        val user = intent.extras?.getParcelable<UserModel>("user")
        intent = Intent(this@AlbumActivity, PhotoActivity::class.java).apply {
            putExtra("album", album)
            putExtra("user",user)
        }
        finish()
        startActivity(intent)
    }

    //boton de cierre de sesion, es necesario para seleccionar otro usuario
    fun closeSesionButtom(){
        binding.closeSesion.setOnClickListener{
            intent = Intent(this@AlbumActivity, LoginActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}

