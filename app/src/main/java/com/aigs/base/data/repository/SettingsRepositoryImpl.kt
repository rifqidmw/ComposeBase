package com.aigs.base.data.repository

import com.aigs.base.common.AppConstants.Language
import com.aigs.base.data.local.LanguagePreferences
import com.aigs.base.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(private val languagePreferences: LanguagePreferences) : SettingsRepository {
    override suspend fun setLanguage(code: String) {
        languagePreferences.setLanguage(code)
    }

    override fun getCurrentLanguage(): Flow<String> {
        return languagePreferences.languageFlow
    }

    override fun getListLanguage(): List<Language> {
        return listOf(
            Language("en", "English"),
            Language("in", "Indonesia")
        )
    }
}