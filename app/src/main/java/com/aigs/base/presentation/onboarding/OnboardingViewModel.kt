package com.aigs.base.presentation.onboarding

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnboardingViewModel : ViewModel() {

    private val _showToast = MutableStateFlow<String?>(null)
    val showToast: StateFlow<String?> = _showToast

    fun onGetStartedClicked() {
        _showToast.value = "Get Started"
    }

    fun onToastShown() {
        _showToast.value = null
    }
}