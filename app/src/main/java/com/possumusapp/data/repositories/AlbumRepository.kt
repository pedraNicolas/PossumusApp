package com.possumusapp.data.repositories

import android.util.Log
import com.possumusapp.core.JsonPlaceInterface
import com.possumusapp.core.JsonPlaceService
import com.possumusapp.data.model.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AlbumRepository {

    fun getData(url: String,callback: (List<Data>) -> Unit) {
        JsonPlaceService.getInstance()
            .create(JsonPlaceInterface::class.java)
            .getAlbumsList(url)
            .enqueue(object : Callback<List<Data>> {
                override fun onResponse(
                    call: Call<List<Data>>,
                    response: Response<List<Data>>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("HTTP Code", "${response.code()}")
                        return
                    }
                    val list = response.body() ?: emptyList()
                    Log.d("Value", list[3].title!!)
                    return callback(list)
                }

                override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                    Log.d("ApiService Failure: ", "${t.message}")
                }
            })
    }

}

