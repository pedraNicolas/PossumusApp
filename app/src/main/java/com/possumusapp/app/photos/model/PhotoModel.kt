package com.possumusapp.app.photos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    @SerializedName("albumId")
    var albumId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String?
):Parcelable{
    constructor():this(null,null,"","","")
}
