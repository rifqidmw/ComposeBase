package com.aigs.base.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<LoginNavigationEvent?>(null)
    val navigationEvent: StateFlow<LoginNavigationEvent?> = _navigationEvent.asStateFlow()

    fun onUsernameChange(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onNextClicked() {
        if (validateInputs()) {
            login()
        }
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }

    private fun login() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val result = repository.login(_uiState.value.username, _uiState.value.password)
            result.fold(
                onSuccess = {
                    _navigationEvent.value = LoginNavigationEvent.NavigateToHome
                },
                onFailure = { e ->
                    val errorMessage = when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                400 -> "Invalid credentials"
                                500 -> "Internal server error"
                                else -> "An unknown error occurred"
                            }
                        }
                        else -> "An error occurred"
                    }
                    _uiState.update { it.copy(error = errorMessage) }
                }
            )
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        _uiState.update { state ->
            state.copy(
                usernameError = validateUsername(state.username).also {
                    if (it != null) isValid = false
                },
                passwordError = validatePassword(state.password).also {
                    if (it != null) isValid = false
                }
            )
        }

        return isValid
    }

    private fun validateUsername(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"

            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password cannot be empty"
            password.length < 8 -> "Password must be at least 8 characters long"

            else -> null
        }
    }
}