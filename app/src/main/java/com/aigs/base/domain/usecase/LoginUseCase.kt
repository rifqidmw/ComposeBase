package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCaseSuspend
import com.aigs.base.data.request.LoginRequest
import com.aigs.base.domain.model.LoginModel
import com.aigs.base.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) : BaseUseCaseSuspend<LoginRequest, Result<LoginModel>>() {
    override suspend fun execute(params: LoginRequest): Result<LoginModel> {
        return authRepository.login(params)
    }
}