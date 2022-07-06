package com.possumusapp.services

import com.possumusapp.app.albums.model.AlbumModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class AlbumServiceTest{

    @RelaxedMockK
    private lateinit var albumServiceInterface: AlbumServiceInterface

    lateinit var albumService: AlbumService

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        albumService = AlbumService(albumServiceInterface)
    }

    @Test
    fun `when The Album Service Interface Response With A Code Other Than 200 With Album Url`()= runBlocking {
        //Given
        coEvery {
            albumServiceInterface.getAlbumsList("/album").enqueue(object :
                Callback<List<AlbumModel>>{
                override fun onResponse(
                    call: Call<List<AlbumModel>>,
                    response: Response<List<AlbumModel>>
                ) {
                }

                override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {

                }

            })
        }
        //When

        //Then
    }


}