package com.possumusapp.app.photos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.services.PhotoService

class PhotoViewModel: ViewModel() {

    val photoModel = MutableLiveData<List<PhotoModel>>()

    fun fetchPhotoData(url: String) {
        PhotoService.fetchData(url) {
            photoModel.postValue(it)
        }
    }
}