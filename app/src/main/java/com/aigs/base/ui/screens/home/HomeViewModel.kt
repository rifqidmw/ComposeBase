package com.aigs.base.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.data.model.Product
import com.aigs.base.data.model.ProductResponse
import com.aigs.base.data.repository.ProductRepository
import com.aigs.base.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<HomeNavigationEvent?>(null)
    val navigationEvent: StateFlow<HomeNavigationEvent?> = _navigationEvent.asStateFlow()

    private var products: List<Product> = emptyList()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                products = repository.getProducts()
                filterProducts(_uiState.value.searchQuery)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "An unknown error occurred"
                    )
                }
            }
        }
    }

    private fun filterProducts(query: String) {
        val data = if (query.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.title.contains(query, ignoreCase = true)
            }
        }
        _uiState.update {
            it.copy(
                products = data,
                isLoading = false
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        filterProducts(query)
    }

    fun onSettingsClicked() {
        _navigationEvent.value = HomeNavigationEvent.NavigateToSettings
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }
}

data class HomeState(
    val products: List<Product> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class HomeNavigationEvent {
    object NavigateToSettings: HomeNavigationEvent()
}