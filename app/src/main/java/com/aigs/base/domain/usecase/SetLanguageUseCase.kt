package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCaseSuspend
import com.aigs.base.domain.repository.SettingsRepository

class SetLanguageUseCase(
    private val settingsRepository: SettingsRepository
) : BaseUseCaseSuspend<String, Unit>() {
    override suspend fun execute(params: String) {
        settingsRepository.setLanguage(params)
    }
}