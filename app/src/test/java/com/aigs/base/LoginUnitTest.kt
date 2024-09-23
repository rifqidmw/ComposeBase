package com.aigs.base

import com.aigs.base.common.AppConstants.Api
import com.aigs.base.data.local.UserPreferences
import com.aigs.base.data.model.LoginRequest
import com.aigs.base.data.remote.AuthService
import com.aigs.base.data.repository.AuthRepository
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.Test

class LoginUnitTest: KoinTest {

    private val authService: AuthService by inject<AuthService>()

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
                            .create(AuthService::class.java)
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
    fun `login success`() = runBlocking {
        val loginRequest = LoginRequest(
            username = "emilys",
            password = "emilyspass"
        )

        val result = authService.login(loginRequest)

        // Assuming the response is successful and contains a user id = 1
        assertEquals(1, result.id)
    }

    @Test
    fun `login failure - returns 400`() = runBlocking {
        val loginRequest = LoginRequest(
            username = "asd",
            password = "asd"
        )

        try {
            val result = authService.login(loginRequest)
        } catch (e: Exception) {
            assertEquals("HTTP 400 Bad Request", e.message)
        }
    }
}