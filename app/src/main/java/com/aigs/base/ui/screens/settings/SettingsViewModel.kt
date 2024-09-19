package com.aigs.base.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.data.domain.usecase.LogoutUseCase
import com.aigs.base.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<SettingsNavigationEvent?>(null)
    val navigationEvent: StateFlow<SettingsNavigationEvent?> = _navigationEvent.asStateFlow()

    init {
        viewModelScope.launch {
            repository.currentLanguage.collect { code ->
                _uiState.update { it.copy(
                    selectedLanguage = code,
                    listLanguages = repository.getListLanguage()
                ) }
            }
        }
    }

    fun setLanguage(code: String) {
        viewModelScope.launch {
            repository.setLanguage(code)
        }
    }

    fun onLogoutClicked() {
        logoutUseCase()
        _navigationEvent.value = SettingsNavigationEvent.Logout
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
}