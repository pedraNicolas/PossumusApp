package com.possumusapp.core

import com.possumusapp.data.model.Albums
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface JsonPlaceInterface {
    @GET
    fun getAlbumsList(@Url url:String): Call<List<Albums>>
}