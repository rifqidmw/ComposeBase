package com.aigs.base.domain.usecase

import com.aigs.base.common.AppConstants
import com.aigs.base.common.base.BaseUseCase
import com.aigs.base.domain.repository.SettingsRepository

class GetLanguageUseCase(
    private val settingsRepository: SettingsRepository
) : BaseUseCase<Unit, List<AppConstants.Language>>(){
    override fun execute(params: Unit): List<AppConstants.Language> {
        return settingsRepository.getListLanguage()
    }
}