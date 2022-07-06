package com.possumusapp.app.login.model

import javax.inject.Inject

class LoginCache @Inject constructor() {
        val users = mutableMapOf<String,UserModel>()
}