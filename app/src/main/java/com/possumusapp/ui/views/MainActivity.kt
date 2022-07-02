package com.possumusapp.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.core.JsonPlaceInterface
import com.possumusapp.core.JsonPlaceService
import com.possumusapp.data.model.Albums
import com.possumusapp.data.model.repositories.AlbumRepository
import com.possumusapp.databinding.ActivityMainBinding
import com.possumusapp.domain.UseCase
import com.possumusapp.ui.adapter.Adapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AlbumRepository.getData {
            initRecyclerView(it)
        }

    }



    private fun initRecyclerView(albumList: List<Albums>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = Adapter(albumList){ onItemSelected(it) }

    }

    private fun onItemSelected(albums: Albums){

    }


}