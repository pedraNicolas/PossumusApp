package com.possumusapp.services

import com.possumusapp.app.albums.model.AlbumModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface AlbumServiceInterface {
    @GET
    suspend fun getAlbumsList(@Url url:String): Response<List<AlbumModel>>
}