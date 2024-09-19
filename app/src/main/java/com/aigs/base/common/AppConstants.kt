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
        const val BASE_URL = "https://dummyjson.com/"
        const val COUNTRIES_BASE_URL = "https://countriesnow.space/api/v0.1/"
    }

    data class Language(
        val code: String,
        val name: String
    )
}