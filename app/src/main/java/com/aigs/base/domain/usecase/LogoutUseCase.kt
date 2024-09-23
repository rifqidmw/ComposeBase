package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCase
import com.aigs.base.data.repository.AuthRepositoryImpl
import com.aigs.base.domain.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) : BaseUseCase<Unit, Unit>() {
    override fun execute(params: Unit) {
        authRepository.logout()
    }
}