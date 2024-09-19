package com.aigs.base.data.repository

import com.aigs.base.common.AppConstants.Language
import com.aigs.base.data.local.LanguagePreferences
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val languagePreferences: LanguagePreferences) {
    val currentLanguage: Flow<String> = languagePreferences.languageFlow

    suspend fun setLanguage(code: String) {
        languagePreferences.setLanguage(code)
    }

    fun getListLanguage(): List<Language> {
        return listOf(
            Language("en", "English"),
            Language("in", "Indonesia")
        )
    }
}