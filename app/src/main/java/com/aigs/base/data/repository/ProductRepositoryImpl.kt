package com.aigs.base.data.repository

import com.aigs.base.data.mapper.toDomain
import com.aigs.base.data.response.Product
import com.aigs.base.data.response.CategoriesResponse
import com.aigs.base.data.remote.ProductsService
import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.model.ProductModel
import com.aigs.base.domain.repository.ProductRepository
import com.aigs.base.utils.Utils.appLog

class ProductRepositoryImpl(private val api: ProductsService) : ProductRepository {
    override suspend fun getProducts(): Result<List<ProductModel>> {
        return try {
            val res = api.getProducts()
            Result.success(res.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCategories(): Result<List<CategoriesModel>> {
        return try {
            val res = api.getCategories()
            Result.success(res.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}