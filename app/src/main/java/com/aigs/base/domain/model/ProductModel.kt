package com.aigs.base.domain.model

import com.aigs.base.data.response.Dimensions
import com.aigs.base.data.response.Meta
import com.aigs.base.data.response.Review

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val images: List<String>,
)
