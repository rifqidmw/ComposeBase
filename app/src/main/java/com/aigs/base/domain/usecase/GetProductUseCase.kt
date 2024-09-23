package com.aigs.base.domain.usecase

import com.aigs.base.common.base.BaseUseCaseSuspend
import com.aigs.base.domain.model.ProductModel
import com.aigs.base.domain.repository.ProductRepository

class GetProductUseCase(
    private val productRepository: ProductRepository
) : BaseUseCaseSuspend<Unit, Result<List<ProductModel>>>(){
    override suspend fun execute(params: Unit): Result<List<ProductModel>> {
        return productRepository.getProducts()
    }
}