package com.possumusapp.services

import com.possumusapp.app.albums.model.AlbumModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface AlbumServiceInterface {
    @GET
    fun getAlbumsList(@Url url:String): Call<List<AlbumModel>>
}