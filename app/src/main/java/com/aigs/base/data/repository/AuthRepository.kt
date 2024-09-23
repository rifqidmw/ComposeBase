package com.aigs.base.data.repository

import com.aigs.base.data.local.UserPreferences
import com.aigs.base.data.model.LoginRequest
import com.aigs.base.data.model.LoginResponse
import com.aigs.base.data.remote.AuthService

class AuthRepository(
    private val api: AuthService,
    private val userPreferences: UserPreferences
) {
    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val res = api.login(LoginRequest(email, password))
            userPreferences.saveToken(res.token)
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isLoggedIn(): Boolean {
        return userPreferences.getToken() != null
    }

    fun logout() {
        userPreferences.clearToken()
    }
}