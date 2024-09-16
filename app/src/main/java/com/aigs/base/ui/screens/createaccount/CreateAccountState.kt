package com.aigs.base.ui.screens.createaccount

import com.aigs.base.data.model.Country

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