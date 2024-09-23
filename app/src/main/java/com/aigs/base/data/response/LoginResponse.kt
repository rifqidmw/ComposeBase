package com.aigs.base.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    var accessToken: String? = null,
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("firstName")
    var firstName: String? = null,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("refreshToken")
    var refreshToken: String? = null,
    @SerializedName("username")
    var username: String? = null
)