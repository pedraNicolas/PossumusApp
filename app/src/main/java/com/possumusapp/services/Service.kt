package com.possumusapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class Service {

    companion object {
        private const val baseUrl = "https://jsonplaceholder.typicode.com/"
        private var retrofit: Retrofit? = null

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }

}