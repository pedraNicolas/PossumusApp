package com.possumusapp.services

import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.photos.model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface PhotoServiceInterface {
    @GET
    fun getPhotoList(@Url url:String): Call<List<PhotoModel>>
}