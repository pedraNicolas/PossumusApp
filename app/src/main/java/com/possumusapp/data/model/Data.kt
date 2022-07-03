package com.possumusapp.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("almbumId")
    val almbumId: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)
