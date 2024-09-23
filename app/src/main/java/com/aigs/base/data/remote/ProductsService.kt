package com.aigs.base.data.remote

import com.aigs.base.common.AppConstants
import com.aigs.base.data.response.ProductResponse
import retrofit2.http.GET

interface ProductsService {
    @GET(AppConstants.Api.PRODUCTS)
    suspend fun getProducts(): ProductResponse
}