package com.possumusapp.app.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.services.AlbumService
import com.possumusapp.services.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginService: LoginService):ViewModel() {
    val userModel = MutableLiveData<UserModel>()

    fun fetchLoginData(url:String) {
        loginService.fetchData(url) {
            userModel.postValue(it)
        }
    }
}