package com.aigs.base.data.model

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val token: String
)