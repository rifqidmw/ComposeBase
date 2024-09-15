package com.aigs.base.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent.asStateFlow()

    fun onGetStartedClicked() {
        _navigationEvent.value = NavigationEvent.NavigateToCreateAccount
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
}

sealed class NavigationEvent {
    object NavigateToCreateAccount : NavigationEvent()
}