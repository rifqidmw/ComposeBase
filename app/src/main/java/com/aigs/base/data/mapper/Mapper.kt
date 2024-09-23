package com.aigs.base.data.mapper

import com.aigs.base.data.response.LoginResponse
import com.aigs.base.domain.model.LoginModel

fun LoginResponse.toDomain(): LoginModel {
    return LoginModel(
        id = this.id,
        username = this.username,
        email = this.email,
        token = this.accessToken
    )
}