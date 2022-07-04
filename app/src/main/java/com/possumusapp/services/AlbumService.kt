package com.possumusapp.services

import android.util.Log
import com.possumusapp.app.albums.model.AlbumModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AlbumService : Service() {

    fun fetchData(url: String, callback: (List<AlbumModel>) -> Unit) {
        getInstance().create(AlbumServiceInterface::class.java)
            .getAlbumsList(url)
            .enqueue(object : Callback<List<AlbumModel>> {
                override fun onResponse(
                    call: Call<List<AlbumModel>>,
                    response: Response<List<AlbumModel>>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("HTTP Code", "${response.code()}")
                        return
                    }
                    val list = response.body() ?: emptyList()
                    return callback(list)
                }

                override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {
                    Log.d("Album Service Failure: ", "${t.message}")
                }
            })
    }

}