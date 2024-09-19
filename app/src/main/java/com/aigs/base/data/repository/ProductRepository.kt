package com.aigs.base.data.repository

import com.aigs.base.data.model.ProductResponse
import com.aigs.base.data.remote.ProductsService

class ProductRepository(private val api: ProductsService) {
    suspend fun getProducts(): List<ProductResponse> {
        return api.getProducts()
    }
}