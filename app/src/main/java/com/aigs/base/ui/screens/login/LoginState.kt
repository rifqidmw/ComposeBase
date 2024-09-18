package com.aigs.base.ui.screens.login

data class LoginState(
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class LoginNavigationEvent {
    object NavigateToHome : LoginNavigationEvent()
}