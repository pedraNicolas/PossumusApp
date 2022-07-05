package com.possumusapp.app.albums.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.services.AlbumService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumService: AlbumService): ViewModel() {

    val albumModel = MutableLiveData<List<AlbumModel>>()

     fun fetchAlbumData(url:String) {
        albumService.fetchData(url) {
            albumModel.postValue(it)
        }
    }

}