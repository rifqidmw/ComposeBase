package com.aigs.base.ui.screens.onboarding

sealed class OnboardingNavigationEvent {
    object NavigateToCreateAccount : OnboardingNavigationEvent()
    object NavigateToLogin : OnboardingNavigationEvent()
}