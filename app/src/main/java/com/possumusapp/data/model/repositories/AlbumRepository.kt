package com.possumusapp.data.model.repositories

import android.util.Log
import com.possumusapp.core.JsonPlaceInterface
import com.possumusapp.core.JsonPlaceService
import com.possumusapp.data.model.Albums
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AlbumRepository {

    fun getData(callback: (List<Albums>) -> Unit) {
        JsonPlaceService.getInstance()
            .create(JsonPlaceInterface::class.java)
            .getAlbumsList("/albums")
            .enqueue(object : Callback<List<Albums>> {
                override fun onResponse(
                    call: Call<List<Albums>>,
                    response: Response<List<Albums>>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("HTTP Code", "${response.code()}")
                        return
                    }
                    val list = response.body() ?: emptyList()
                    Log.d("Value", list[3].title!!)
                    return callback(list)
                }

                override fun onFailure(call: Call<List<Albums>>, t: Throwable) {
                    Log.d("ApiService Failure: ", "${t.message}")
                }
            })
    }

}

