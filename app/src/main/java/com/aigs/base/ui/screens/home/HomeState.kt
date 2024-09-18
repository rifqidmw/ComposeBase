package com.aigs.base.ui.screens.home

import com.aigs.base.data.model.ProductResponse

data class HomeState(
    val products: List<ProductResponse> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class HomeNavigationEvent {
    object Logout: HomeNavigationEvent()
}