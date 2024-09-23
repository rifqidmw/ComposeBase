package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCase
import com.aigs.base.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentLanguageUseCase(
    private val settingsRepository: SettingsRepository
) : BaseUseCase<Unit, Flow<String>>(){
    override fun execute(params: Unit): Flow<String> {
        return settingsRepository.getCurrentLanguage()
    }

}