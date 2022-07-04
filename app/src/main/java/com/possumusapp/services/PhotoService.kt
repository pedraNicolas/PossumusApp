package com.possumusapp.services

import android.util.Log
import com.possumusapp.app.photos.model.PhotoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PhotoService: Service() {
    fun fetchData(url: String, callback: (List<PhotoModel>) -> Unit) {
        getInstance().create(PhotoServiceInterface::class.java)
            .getPhotoList(url)
            .enqueue(object : Callback<List<PhotoModel>> {
                override fun onResponse(
                    call: Call<List<PhotoModel>>,
                    response: Response<List<PhotoModel>>
                ) {
                    if (!response.isSuccessful) {
                        Log.d("HTTP Code", "${response.code()}")
                        return
                    }
                    val list = response.body() ?: emptyList()
                    return callback(list)
                }

                override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                    Log.d("Album Service Failure: ", "${t.message}")
                }
            })
    }
}