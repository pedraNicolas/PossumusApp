package com.possumusapp.services

import com.possumusapp.app.photos.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface PhotoServiceInterface {
    @GET
    suspend fun getPhotoList(@Url url:String): Response<List<PhotoModel>>
}