package com.possumusapp.app.photos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.model.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoRepository: PhotoRepository): ViewModel() {

    val photoModel = MutableLiveData<List<PhotoModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = photoRepository.getAllPhotoQuotes(url)
            if (result.isNotEmpty()) {
                photoModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}
