package com.aigs.base.data.remote

import com.aigs.base.common.AppConstants
import com.aigs.base.data.response.ProductResponse
import com.aigs.base.data.response.CategoriesResponse
import retrofit2.http.GET

interface ProductsService {
    @GET(AppConstants.Api.PRODUCTS)
    suspend fun getProducts(): ProductResponse

    @GET(AppConstants.Api.PRODUCT_CATEGORIES)
    suspend fun getCategories(): List<CategoriesResponse>
}