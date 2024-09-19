package com.aigs.base.ui.screens.settings

import com.aigs.base.common.AppConstants.Language

data class SettingsState(
    val selectedLanguage: String = "en",
    val listLanguages: List<Language> = emptyList()
)

sealed class SettingsNavigationEvent {
    object Logout: SettingsNavigationEvent()
}