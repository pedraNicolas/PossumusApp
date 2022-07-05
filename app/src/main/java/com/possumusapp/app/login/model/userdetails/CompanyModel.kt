package com.possumusapp.app.login.model.userdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyModel(
    @SerializedName("name")
    var name: String?,
    @SerializedName("catchPhrase")
    var catchPhrase: String?,
    @SerializedName("bs")
    var bs: String?
): Parcelable {
    constructor(): this("","","")
}
