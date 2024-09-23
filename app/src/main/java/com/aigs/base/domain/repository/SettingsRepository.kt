package com.aigs.base.domain.repository

import com.aigs.base.common.AppConstants
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setLanguage(code: String)
    fun getCurrentLanguage() : Flow<String>
    fun getListLanguage() : List<AppConstants.Language>
}