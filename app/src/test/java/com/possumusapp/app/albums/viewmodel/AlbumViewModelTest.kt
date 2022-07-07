package com.possumusapp.app.albums.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.possumusapp.app.albums.model.AlbumModel
import com.possumusapp.app.albums.model.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumViewModelTest {

    @RelaxedMockK
    private lateinit var albumRepository: AlbumRepository

    private lateinit var albumViewModel: AlbumViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        albumViewModel = AlbumViewModel(albumRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Album Repository Returns An Empty List`() = runTest {
        //Given
        val album = emptyList<AlbumModel>()
        coEvery { albumRepository.getAllAlbumQuotes("/album") } returns album
        //When
        albumViewModel.onCreate("/album")
        //Then
        assert(albumViewModel.isLoading.value == true)
    }
    @Test
    fun `when Album Repository Returns A List`() = runTest{
        val albumModel = AlbumModel(1,1,"prueba")
        val album = listOf<AlbumModel>(albumModel)
        coEvery { albumRepository.getAllAlbumQuotes("/album") } returns album
        //When
        albumViewModel.onCreate("/album")
        //Then
        assert(albumViewModel.isLoading.value == false)
        assert(albumViewModel.albumModel.value == album)
    }

}