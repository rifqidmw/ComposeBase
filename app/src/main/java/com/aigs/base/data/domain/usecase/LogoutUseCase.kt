package com.aigs.base.data.domain.usecase

import com.aigs.base.data.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {
    operator fun invoke() {
        repository.logout()
    }
}