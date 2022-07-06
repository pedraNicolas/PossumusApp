package com.possumusapp.app.albums.model

import com.possumusapp.services.AlbumService
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val albumService: AlbumService,private val albumCache: AlbumCache) {

    suspend fun getAllAlbumQuotes(url: String): List<AlbumModel> {
        if (albumCache.albums[url]==null) {
            val response = albumService.getAlbumQuotes(url)
            albumCache.albums[url]=response
            return response
        } else {
            return albumCache.albums[url]!!
        }
    }
}