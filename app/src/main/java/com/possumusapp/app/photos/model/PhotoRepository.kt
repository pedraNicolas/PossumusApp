package com.possumusapp.app.photos.model

import android.content.Context
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.login.view.LoginActivity
import com.possumusapp.commons.NetworkStatusInterface
import com.possumusapp.services.PhotoService
import javax.inject.Inject

class PhotoRepository
@Inject
constructor(
    private val photoService: PhotoService,
) {
    suspend fun getAllPhotoQuotes(url: String): List<PhotoModel> {
        val list = photoService.getPhotoQuotes(url)
        return list.filter { PhotoModel -> PhotoModel.albumId != null }
    }
}