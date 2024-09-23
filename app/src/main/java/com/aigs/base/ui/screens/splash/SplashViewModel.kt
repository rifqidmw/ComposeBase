package com.aigs.base.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.common.AppConstants.Route
import com.aigs.base.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: AuthRepositoryImpl) : ViewModel() {
    private val _initialRoute = MutableStateFlow<String?>(null)
    val initialRoute: StateFlow<String?> = _initialRoute

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            _initialRoute.value = if (repository.isLoggedIn()) {
                Route.HOME
            } else {
                Route.ONBOARDING
            }
        }
    }
}