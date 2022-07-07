package com.possumusapp.app.photos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.possumusapp.app.photos.model.PhotoModel
import com.possumusapp.app.photos.model.PhotoRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class PhotoViewModelTest{
    @RelaxedMockK
    private lateinit var photoRepository: PhotoRepository

    private lateinit var photoViewModel: PhotoViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        photoViewModel = PhotoViewModel(photoRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when Photo Repository Returns An Empty List`() = runTest {
        //Given
        val photo = emptyList<PhotoModel>()
        coEvery { photoRepository.getAllPhotoQuotes("/photos") } returns photo
        //When
        photoViewModel.onCreate("/photos")
        //Then
        assert(photoViewModel.isLoading.value == true)
    }
    @Test
    fun `when Photo Repository Returns A List`() = runTest{
        val photoModel = PhotoModel(1,1,"","","")
        val photo = listOf<PhotoModel>(photoModel)
        coEvery { photoRepository.getAllPhotoQuotes("/photos") } returns photo
        //When
        photoViewModel.onCreate("/photos")
        //Then
        assert(photoViewModel.isLoading.value == false)
        assert(photoViewModel.photoModel.value == photo)
    }
}