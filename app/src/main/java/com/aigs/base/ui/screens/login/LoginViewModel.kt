package com.aigs.base.ui.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<LoginNavigationEvent?>(null)
    val navigationEvent: StateFlow<LoginNavigationEvent?> = _navigationEvent.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onNextClicked() {
        val emailError = validateEmail(_uiState.value.email)
        if (emailError != null) {
            _uiState.update { it.copy(emailError = emailError) }
        } else {
            _navigationEvent.value = LoginNavigationEvent.NavigateToHome
        }
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> "Please enter a valid email address"

            else -> null
        }
    }
}