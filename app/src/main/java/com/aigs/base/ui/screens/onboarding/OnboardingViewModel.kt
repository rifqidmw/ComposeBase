package com.aigs.base.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val _navigationEvent = MutableStateFlow<OnboardingNavigationEvent?>(null)
    val navigationEvent: StateFlow<OnboardingNavigationEvent?> = _navigationEvent.asStateFlow()

    fun onGetStartedClicked() {
        _navigationEvent.value = OnboardingNavigationEvent.NavigateToCreateAccount
    }

    fun onLoginClicked() {
        _navigationEvent.value = OnboardingNavigationEvent.NavigateToLogin
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
}