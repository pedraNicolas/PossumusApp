package com.possumusapp.services.di

import com.possumusapp.services.AlbumServiceInterface
import com.possumusapp.services.LoginServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginServiceInterface {
        return retrofit.create(LoginServiceInterface::class.java)
    }
}