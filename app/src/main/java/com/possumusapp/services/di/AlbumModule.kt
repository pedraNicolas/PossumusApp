package com.possumusapp.services.di

import com.possumusapp.app.albums.model.AlbumCache
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.services.AlbumServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {

    @Singleton
    @Provides
    fun provideAlbumService(retrofit: Retrofit): AlbumServiceInterface {
        return retrofit.create(AlbumServiceInterface::class.java)
    }

}