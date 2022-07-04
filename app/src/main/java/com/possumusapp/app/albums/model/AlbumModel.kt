package com.possumusapp.app.albums.model


import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?
)
