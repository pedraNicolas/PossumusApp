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

     fun getLoginQuotes(url: String, callback: (UserModel) -> Unit) {
        api.getLoginList(url).enqueue(object : Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                if (!response.isSuccessful) {
                    Log.d("HTTP Code", "${response.code()}")
                    return
                }
                val user = response.body()
                if (user==null){
                    Log.d("Callback","User is Null")
                }else {
                    return callback(user)
                }
            }
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.d("Album Service Failure: ", "${t.message}")
            }
        })
    }

}