package com.possumusapp.app.photos.model

import com.possumusapp.app.albums.model.AlbumModel
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class PhotoCache @Inject constructor() {
        val photos = mutableMapOf<String,List<PhotoModel>>()

}