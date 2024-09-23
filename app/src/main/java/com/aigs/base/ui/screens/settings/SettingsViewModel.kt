package com.aigs.base.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.common.AppConstants
import com.aigs.base.domain.usecase.LogoutUseCase
import com.aigs.base.data.repository.SettingsRepositoryImpl
import com.aigs.base.domain.usecase.GetCurrentLanguageUseCase
import com.aigs.base.domain.usecase.GetLanguageUseCase
import com.aigs.base.domain.usecase.SetLanguageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getCurrentLanguageUseCase: GetCurrentLanguageUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsState())
    val uiState: StateFlow<SettingsState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<SettingsNavigationEvent?>(null)
    val navigationEvent: StateFlow<SettingsNavigationEvent?> = _navigationEvent.asStateFlow()

    init {
        viewModelScope.launch {
            val res = getCurrentLanguageUseCase.execute(Unit).collect { code ->
                _uiState.update {
                    it.copy(
                        selectedLanguage = code,
                        listLanguages = getLanguageUseCase.execute(Unit)
                    )
                }

            }
        }
    }

    fun setLanguage(code: String) {
        viewModelScope.launch {
            setLanguageUseCase.execute(code)
        }
    }

    fun onLogoutClicked() {
        logoutUseCase.execute(Unit)
        _navigationEvent.value = SettingsNavigationEvent.Logout
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
}

data class SettingsState(
    val selectedLanguage: String = "en",
    val listLanguages: List<AppConstants.Language> = emptyList()
)

sealed class SettingsNavigationEvent {
    object Logout : SettingsNavigationEvent()
}