package com.possumusapp.app.albums.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumModel(
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?
):Parcelable{
    constructor(): this(null,null,"")
}
