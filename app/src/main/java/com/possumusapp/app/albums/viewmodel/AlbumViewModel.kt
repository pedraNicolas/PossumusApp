package com.possumusapp.app.albums.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.view.AlbumActivity
import com.possumusapp.services.AlbumService
import kotlin.concurrent.thread

class AlbumViewModel: ViewModel() {


    val albumModel = MutableLiveData<List<AlbumModel>>()

     fun fetchAlbumData() {
        AlbumService.fetchData("/albums") {
            albumModel.postValue(it)
        }
    }

}