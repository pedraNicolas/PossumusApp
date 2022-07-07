package com.possumusapp.app.login.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginCache @Inject constructor() {
        val users = mutableMapOf<String,UserModel>()
}