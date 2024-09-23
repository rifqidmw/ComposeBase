package com.aigs.base.domain.usecase

import com.aigs.base.data.repository.AuthRepositoryImpl

class LogoutUseCase(private val repository: AuthRepositoryImpl) {
    operator fun invoke() {
        repository.logout()
    }
}