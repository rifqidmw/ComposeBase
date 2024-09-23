package com.aigs.base.domain.repository

import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProducts() : Result<List<ProductModel>>
    suspend fun getCategories() : Result<List<CategoriesModel>>
}