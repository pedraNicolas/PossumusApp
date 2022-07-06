package com.possumusapp.services

import android.util.Log
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.photos.model.PhotoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PhotoService @Inject constructor(
    private val api: PhotoServiceInterface){

    suspend fun getPhotoQuotes(url: String):List<PhotoModel>{
        return withContext(Dispatchers.IO){
            api.getPhotoList(url).body() ?: emptyList()
        }
    }
}