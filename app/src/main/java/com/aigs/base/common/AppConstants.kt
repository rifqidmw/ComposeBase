package com.aigs.base.common

object AppConstants {
    object Route {
        const val ONBOARDING = "onboarding"
        const val LOGIN = "login"
        const val CREATE_ACCOUNT = "create_account"
        const val HOME = "home"
        const val SETTINGS = "settings"
    }

    object Api {
        const val COUNTRIES_BASE_URL = "https://countriesnow.space/api/v0.1/"

        const val LOGIN = "auth/login"
        const val FLAGS = "countries/flag/images"
        const val PRODUCTS = "products"
        const val PRODUCT_CATEGORIES = "products/categories"
    }

    data class Language(
        val code: String,
        val name: String
    )
}