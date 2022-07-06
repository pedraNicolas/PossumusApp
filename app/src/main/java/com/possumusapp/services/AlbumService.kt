package com.possumusapp.services

import android.util.Log
import com.possumusapp.app.albums.model.AlbumModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AlbumService @Inject constructor(
    private val api: AlbumServiceInterface) {

    suspend fun getAlbumQuotes(url: String):List<AlbumModel>{
        return withContext(Dispatchers.IO){
            api.getAlbumsList(url).body() ?: emptyList()
        }
    }
}