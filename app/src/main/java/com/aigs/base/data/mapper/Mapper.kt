package com.aigs.base.data.mapper

import com.aigs.base.data.response.CategoriesResponse
import com.aigs.base.data.response.LoginResponse
import com.aigs.base.data.response.ProductResponse
import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.model.LoginModel
import com.aigs.base.domain.model.ProductModel

fun LoginResponse.toDomain(): LoginModel {
    return LoginModel(
        id = this.id,
        username = this.username,
        email = this.email,
        token = this.accessToken
    )
}

fun ProductResponse.toDomain(): List<ProductModel> {
    return products.map {
        ProductModel(
           id = it.id,
            title = it.title,
            description = it.description,
            category = it.category,
            price = it.price,
            images = it.images,
        )
    }
}

fun List<CategoriesResponse>.toDomain() : List<CategoriesModel> {
    return this.map {
        CategoriesModel(
            slug = it.slug,
            name = it.name,
            url = it.url
        )
    }
}