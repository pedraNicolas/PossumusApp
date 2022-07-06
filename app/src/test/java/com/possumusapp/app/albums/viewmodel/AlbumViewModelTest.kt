package com.possumusapp.app.albums.viewmodel

import com.possumusapp.services.AlbumService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before


internal class AlbumViewModelTest{

    @RelaxedMockK
    private lateinit var albumService: AlbumService
    lateinit var albumViewModel: AlbumViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        albumViewModel = AlbumViewModel(albumService)
    }
}