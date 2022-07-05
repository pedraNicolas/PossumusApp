package com.possumusapp.services.di

import com.possumusapp.services.PhotoServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotoModule {

    @Singleton
    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoServiceInterface {
        return retrofit.create(PhotoServiceInterface::class.java)
    }
}
