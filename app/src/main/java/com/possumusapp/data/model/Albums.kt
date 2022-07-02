package com.possumusapp.data.model


import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("userId")
    var userId: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("title")
    var title: String?
)
