package com.possumusapp.app.login.model.userdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoModel(
    @SerializedName("lat")
    var lat: String?,
    @SerializedName("lng")
    var lng: String?,
): Parcelable {
    constructor(): this("","")
}
