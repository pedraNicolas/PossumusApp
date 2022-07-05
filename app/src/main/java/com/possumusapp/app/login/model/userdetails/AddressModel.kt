package com.possumusapp.app.login.model.userdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class AddressModel(
    @SerializedName("street")
    var street: String?,
    @SerializedName("suite")
    var suite: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("zipcode")
    var zipcode: String?,
    @SerializedName("geo")
    var geo:@RawValue GeoModel?
): Parcelable {
    constructor(): this(null,"","","",null)
}