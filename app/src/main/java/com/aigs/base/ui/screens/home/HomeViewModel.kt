package com.aigs.base.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aigs.base.data.response.Product
import com.aigs.base.data.response.CategoriesResponse
import com.aigs.base.data.repository.ProductRepositoryImpl
import com.aigs.base.data.request.LoginRequest
import com.aigs.base.domain.model.CategoriesModel
import com.aigs.base.domain.model.ProductModel
import com.aigs.base.domain.usecase.GetCategoriesUseCase
import com.aigs.base.domain.usecase.GetProductUseCase
import com.aigs.base.ui.screens.login.LoginNavigationEvent
import com.aigs.base.utils.Utils.appLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<HomeNavigationEvent?>(null)
    val navigationEvent: StateFlow<HomeNavigationEvent?> = _navigationEvent.asStateFlow()


    init {
        fetchCategories()
        fetchProducts()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getCategoriesUseCase.execute(Unit)
            result.fold(
                onSuccess = { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            categories = data,
                        )
                    }
                },
                onFailure = { e ->
                    val errorMessage = when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                400 -> "Invalid credentials"
                                500 -> "Internal server error"
                                else -> "An unknown error occurred"
                            }
                        }
                        else -> "An error occurred"
                    }
                    _uiState.update { it.copy(error = errorMessage) }
                }
            )
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = getProductUseCase.execute(Unit)
            result.fold(
                onSuccess = { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = data,
                        )
                    }
                    filterProducts(_uiState.value.searchQuery)
                },
                onFailure = { e ->
                    val errorMessage = when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                400 -> "Invalid credentials"
                                500 -> "Internal server error"
                                else -> "An unknown error occurred"
                            }
                        }
                        else -> "An error occurred"
                    }
                    _uiState.update { it.copy(error = errorMessage) }
                }
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

    fun onCategorySelected(category: String) {
        _uiState.update {
            if (it.selectedCategory == category) {
                it.copy(selectedCategory = "")
            } else {
                it.copy(selectedCategory = category)
            }
        }
        filterCategory(_uiState.value.selectedCategory)
    }

    fun onNavigationEventHandled() {
        _navigationEvent.value = null
    }

    private fun filterProducts(query: String) {
        val products = _uiState.value.products
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

    private fun filterCategory(selectedCategory: String) {
        val products = _uiState.value.products
        val data = if (selectedCategory.isBlank()) {
            products
        } else {
            products.filter { product ->
                product.category.contains(selectedCategory)
            }
        }
        _uiState.update {
            it.copy(
                products = data,
                isLoading = false
            )
        }
    }
}

data class HomeState(
    val authName: String = "Emily",
    val categories: List<CategoriesModel> = emptyList(),
    val products: List<ProductModel> = emptyList(),
    val searchQuery: String = "",
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class HomeNavigationEvent {
    object NavigateToSettings : HomeNavigationEvent()
}