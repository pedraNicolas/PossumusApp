package com.possumusapp.app.albums.model

import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumCache @Inject constructor() {
    val albums = mutableMapOf<String,List<AlbumModel>>()
}