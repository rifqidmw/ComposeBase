package com.aigs.base.data.remote

import com.aigs.base.data.model.LoginRequest
import com.aigs.base.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}