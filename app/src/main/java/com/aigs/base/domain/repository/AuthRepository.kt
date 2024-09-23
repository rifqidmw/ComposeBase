package com.aigs.base.domain.repository

import com.aigs.base.data.request.LoginRequest
import com.aigs.base.domain.model.LoginModel

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest) : Result<LoginModel>
    fun isLoggedIn() : Boolean
    fun logout()
}