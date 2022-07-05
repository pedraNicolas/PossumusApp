package com.possumusapp.app.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.possumusapp.app.login.model.userdetails.AddressModel
import com.possumusapp.app.login.model.userdetails.CompanyModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserModel(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("address")
    var address: @RawValue AddressModel?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("website")
    var website: String?,
    @SerializedName("company")
    var company: @RawValue CompanyModel?
): Parcelable {
    constructor(): this(null,"","","",null,"","",null)
}
