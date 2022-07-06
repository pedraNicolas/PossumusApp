package com.possumusapp.app.albums.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.model.AlbumRepository
import com.possumusapp.services.AlbumService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository): ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val albumModel = MutableLiveData<List<AlbumModel>>()

    fun onCreate(url: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = albumRepository.getAllAlbumQuotes(url)
            if (result.isNotEmpty()) {
                albumModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

}