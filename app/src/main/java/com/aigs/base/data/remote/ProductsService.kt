package com.aigs.base.data.remote

import com.aigs.base.data.model.ProductResponse
import retrofit2.http.GET

interface ProductsService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}