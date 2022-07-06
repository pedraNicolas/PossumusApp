package com.possumusapp.app.photos.model

import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.services.PhotoService
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val photoCache: PhotoCache,private val photoService: PhotoService) {
    suspend fun getAllPhotoQuotes(url: String): List<PhotoModel> {
        if (photoCache.photos[url]==null) {
            val response = photoService.getPhotoQuotes(url)
            photoCache.photos[url]=response
            return response
        } else {
            return photoCache.photos[url]!!
        }
    }
}