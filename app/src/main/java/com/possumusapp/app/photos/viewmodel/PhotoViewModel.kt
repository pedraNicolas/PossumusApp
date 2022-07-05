package com.possumusapp.app.photos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.services.AlbumService
import com.possumusapp.services.PhotoService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoService: PhotoService): ViewModel() {

    val photoModel = MutableLiveData<List<PhotoModel>>()

    fun fetchPhotoData(url: String) {
        photoService.fetchData(url) {
            photoModel.postValue(it)
        }
    }
}