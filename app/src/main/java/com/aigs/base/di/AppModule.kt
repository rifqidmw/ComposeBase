package com.aigs.base.di

import com.aigs.base.common.AppConstants.Api
import com.aigs.base.domain.usecase.LogoutUseCase
import com.aigs.base.data.local.LanguagePreferences
import com.aigs.base.data.local.UserPreferences
import com.aigs.base.data.remote.AuthService
import com.aigs.base.data.remote.CountriesService
import com.aigs.base.data.remote.ProductsService
import com.aigs.base.data.repository.AuthRepositoryImpl
import com.aigs.base.data.repository.CountryRepository
import com.aigs.base.data.repository.ProductRepository
import com.aigs.base.data.repository.SettingsRepository
import com.aigs.base.ui.screens.createaccount.CreateAccountViewModel
import com.aigs.base.ui.screens.home.HomeViewModel
import com.aigs.base.ui.screens.login.LoginViewModel
import com.aigs.base.ui.screens.onboarding.OnboardingViewModel
import com.aigs.base.ui.screens.settings.SettingsViewModel
import com.aigs.base.ui.screens.splash.SplashViewModel
import com.aigs.base.BuildConfig
import com.aigs.base.domain.usecase.LoginUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    val api =
    single { UserPreferences(androidContext()) }
    single { LanguagePreferences(get()) }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Api.COUNTRIES_BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesService::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsService::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_KEY)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    single { CountryRepository(get()) }
    single { ProductRepository(get()) }
    single { AuthRepositoryImpl(get(), get()) }
    single { SettingsRepository(get()) }

    factory { LogoutUseCase(get()) }
    factory { LoginUseCase(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { CreateAccountViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
}