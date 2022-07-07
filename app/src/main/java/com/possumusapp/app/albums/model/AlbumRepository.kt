package com.possumusapp.app.albums.model

import com.possumusapp.commons.NetworkStatusInterface
import com.possumusapp.services.AlbumService
import javax.inject.Inject

class AlbumRepository
@Inject constructor(
    private val albumService: AlbumService,
    private val albumCache: AlbumCache,
    private val networkStatusInterface: NetworkStatusInterface
) {
    suspend fun getAllAlbumQuotes(url: String): List<AlbumModel> {
        val list = albumService.getAlbumQuotes(url)
        return list.filter { AlbumModel -> AlbumModel.userId != null }
    }
}