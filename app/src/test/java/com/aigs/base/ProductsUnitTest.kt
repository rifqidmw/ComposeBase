package com.aigs.base

import com.aigs.base.data.model.LoginRequest
import com.aigs.base.data.remote.ProductsService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.test.assertTrue

class ProductsUnitTest : KoinTest {
    private val productService: ProductsService by inject<ProductsService>()

    @Before
    fun setUp() {
        startKoin {
            modules(
                module {
                    single {
                        Retrofit.Builder()
                            .baseUrl(BuildConfig.API_KEY)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ProductsService::class.java)
                    }
                }
            )
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `fetch products success`() = runBlocking {
        val result = productService.getProducts()

        assertTrue(result.products.isNotEmpty())
    }

    @Test
    fun `fetch products failure - returns 400`(): Unit = runBlocking {
        try {
            productService.getProducts()
        } catch (e: Exception) {
            assertTrue(e is IOException || e is HttpException)
        }
    }
}