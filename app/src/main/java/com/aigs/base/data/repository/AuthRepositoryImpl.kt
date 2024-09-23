package com.aigs.base.data.repository

import com.aigs.base.data.local.UserPreferences
import com.aigs.base.data.mapper.toDomain
import com.aigs.base.data.request.LoginRequest
import com.aigs.base.data.remote.AuthService
import com.aigs.base.domain.model.LoginModel
import com.aigs.base.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthService,
    private val userPreferences: UserPreferences
) : AuthRepository{
    override suspend fun login(loginRequest: LoginRequest): Result<LoginModel> {
        return try {
            val res = api.login(loginRequest)
            userPreferences.saveToken(res.token)
            Result.success(res.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun isLoggedIn(): Boolean {
        return userPreferences.getToken() != null
    }

    override fun logout() {
        userPreferences.clearToken()
    }
}