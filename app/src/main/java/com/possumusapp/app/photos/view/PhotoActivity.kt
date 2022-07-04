package com.possumusapp.app.photos.view

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
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.app.albums.viewmodel.AlbumViewModel
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.view.adapter.PhotoAdapter
import com.possumusapp.app.photos.viewmodel.PhotoViewModel
import com.possumusapp.databinding.ActivityPhotoBinding

class PhotoActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding: ActivityPhotoBinding
    lateinit var url: String
    private val viewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dropDownMenu()
        val photo = intent.extras?.getParcelable<AlbumModel>("album")
        var photoUserId = photo?.userId.toString()
        println(photoUserId)
        url = "/albums/$photoUserId/photos"
        print(url)
        viewModel.photoModel.observe(this, Observer {
            initRecyclerView(it)
        })
        if (photoUserId == "null") {
            url = "/photos"
        }else {
            url = "/albums/${photoUserId}/photos"
        }
        viewModel.fetchPhotoData(url)

    }

    private fun initRecyclerView(list: List<PhotoModel>) {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewId.adapter = PhotoAdapter(list) { onItemSelected(it) }
    }

    private fun onItemSelected(data: PhotoModel) {
//        intent = Intent(this@AlbumActivity, PhotosActivity::class.java).apply {
//            putExtra("album",albums.id)
//        }
//        startActivity(intent)
    }
    private fun dropDownMenu(){
        val endpoints = resources.getStringArray(R.array.endpoints)
        val adapter = ArrayAdapter(
            this,
            R.layout.drop_down_item,
            endpoints
        )
        with(binding.autoCompleteTextView){
            setAdapter(adapter)
            onItemClickListener = this@PhotoActivity
        }
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()

        when (item) {
            "Photos" ->{}

            "Albums" ->{intent = Intent(this@PhotoActivity, AlbumActivity::class.java).apply{}
                startActivity(intent)}
        }

    }
}