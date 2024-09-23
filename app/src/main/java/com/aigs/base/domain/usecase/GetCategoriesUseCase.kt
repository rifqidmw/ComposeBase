package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCaseSuspend
import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.repository.ProductRepository

class GetCategoriesUseCase(
    private val productRepository: ProductRepository
): BaseUseCaseSuspend<Unit, Result<List<CategoriesModel>>>() {
    override suspend fun execute(params: Unit): Result<List<CategoriesModel>> {
        return productRepository.getCategories()
    }
}