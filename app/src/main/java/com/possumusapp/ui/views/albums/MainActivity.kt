package com.possumusapp.ui.views.albums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.data.model.Data
import com.possumusapp.data.repositories.AlbumRepository
import com.possumusapp.databinding.ActivityMainBinding
import com.possumusapp.ui.views.albums.adapter.Adapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AlbumRepository.getData("/albums") {
            initRecyclerView(it)
        }

    }



    private fun initRecyclerView(albumList: List<Data>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = Adapter(albumList){ onItemSelected(it) }

    }

    private fun onItemSelected(data: Data){
        data.userId
        AlbumRepository.getData("/albums/${data.userId}/photos") {
            initRecyclerView(it)
        }

//
//        intent = Intent(this@MainActivity, PhotosActivity::class.java).apply {
//            putExtra("album",albums.id)
//        }
//        startActivity(intent)
    }


}