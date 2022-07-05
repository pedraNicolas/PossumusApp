package com.possumusapp.services

import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.photos.model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface LoginServiceInterface {
    @GET
    fun getLoginList(@Url url:String): Call<UserModel>
}