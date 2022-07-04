package com.possumusapp.app.albums.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.databinding.ActivityMainBinding
import com.possumusapp.app.albums.view.adapter.Adapter
import com.possumusapp.app.albums.viewmodel.AlbumViewModel


class AlbumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.albumModel.observe(this, Observer {
            initRecyclerView(it)
        })
        viewModel.fetchAlbumData()
    }

    private fun initRecyclerView(list: List<AlbumModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = Adapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(data: AlbumModel) {
//        intent = Intent(this@AlbumActivity, PhotosActivity::class.java).apply {
//            putExtra("album",albums.id)
//        }
//        startActivity(intent)
    }

}