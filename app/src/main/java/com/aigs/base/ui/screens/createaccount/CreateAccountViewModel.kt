package com.aigs.base.ui.screens.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.data.response.Country
import com.aigs.base.data.repository.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val repository: CountryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateAccountState())
    val uiState: StateFlow<CreateAccountState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<CreateAccountNavigationEvent?>(null)
    val navigationEvent: StateFlow<CreateAccountNavigationEvent?> = _navigationEvent.asStateFlow()

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val countries = repository.getCountries()
                _uiState.update {
                    it.copy(
                        countries = countries,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message ?: "An unknown error occurred"
                ) }
            }
        }
    }

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        _uiState.update { it.copy(phoneNumber = phoneNumber) }
    }

    fun onCountrySelected(country: Country) {
        _uiState.update { it.copy(selectedCountry = country) }
    }

    fun onDoneClick() {
        if (validateInputs()) {
            _navigationEvent.value = CreateAccountNavigationEvent.NavigateToLogin
        }
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        _uiState.update { state ->
            state.copy(
                emailError = validateEmail(state.email).also { if (it != null) isValid = false },
                passwordError = validatePassword(state.password).also {
                    if (it != null) isValid = false
                },
                phoneNumberError = validatePhoneNumber(state.phoneNumber).also {
                    if (it != null) isValid = false
                }
            )
        }

        return isValid
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() -> "Please enter a valid email address"

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

    private fun validatePhoneNumber(phoneNumber: String): String? {
        return when {
            phoneNumber.isBlank() -> "Phone number cannot be empty"
            phoneNumber.length < 12 -> "Please enter a valid phone number"
            !phoneNumber.all { it.isDigit() } -> "Phone number should only contain digits"
            else -> null
        }
    }
}

data class CreateAccountState(
    val email: String = "",
    val password: String = "",
    val phoneNumber: String = "",
    val countries: List<Country> = emptyList(),
    val selectedCountry: Country? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val phoneNumberError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class CreateAccountNavigationEvent {
    object NavigateToLogin : CreateAccountNavigationEvent()
}