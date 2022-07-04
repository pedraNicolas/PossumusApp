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
import com.possumusapp.app.photos.view.PhotoActivity


class AlbumActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityAlbumBinding
    private val viewModel: AlbumViewModel by viewModels()
    var url = "/albums"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dropDownMenu()
        viewModel.albumModel.observe(this, Observer {
            initRecyclerView(it)
        })
        viewModel.fetchAlbumData()
    }



    private fun initRecyclerView(list: List<AlbumModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = AlbumAdapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(album: AlbumModel) {
        intent = Intent(this@AlbumActivity, PhotoActivity::class.java).apply {
            putExtra("album",album)
        }
        startActivity(intent)
    }
    // Funciones del Drop Down Menu
    private fun dropDownMenu(){
        val endpoints = resources.getStringArray(R.array.endpoints)
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_item,
            endpoints
        )
        with(binding.autoCompleteTextView){
            setAdapter(adapter)
            onItemClickListener = this@AlbumActivity
        }
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

            when (item) {
                "Photos" ->{
                    intent = Intent(this@AlbumActivity, PhotoActivity::class.java).apply{
                }
                    startActivity(intent)}

                "Albums" ->{}
            }

        }

    }

