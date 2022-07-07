package com.possumusapp.app.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.possumusapp.app.login.model.LoginCache
import com.possumusapp.app.login.model.UserModel
import com.possumusapp.services.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService,
) : ViewModel() {
    val userModel = MutableLiveData<Response<UserModel>>()

    fun onCreate(url: String) {
        loginService.getLoginQuotes(url) {
            userModel.postValue(it)
        }
    }
}

