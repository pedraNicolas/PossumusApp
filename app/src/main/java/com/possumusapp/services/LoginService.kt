package com.possumusapp.services

import android.util.Log
import android.widget.Toast
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.app.photos.model.PhotoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginService @Inject constructor(
    private val api: LoginServiceInterface){

     fun getLoginQuotes(url: String, callback: (Response<UserModel>) -> Unit) {
        api.getLoginList(url).enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                return callback(response)
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.d("Album Service Failure: ", "${t.message}")
            }
        })
    }

}