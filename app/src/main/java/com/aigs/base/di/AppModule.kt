package com.aigs.base.di

import com.aigs.base.common.AppConstants.Api
import com.aigs.base.domain.usecase.LogoutUseCase
import com.aigs.base.data.local.LanguagePreferences
import com.aigs.base.data.local.UserPreferences
import com.aigs.base.data.remote.AuthService
import com.aigs.base.data.remote.CountriesService
import com.aigs.base.data.remote.ProductsService
import com.aigs.base.data.repository.AuthRepositoryImpl
import com.aigs.base.data.repository.CountryRepositoryImpl
import com.aigs.base.data.repository.ProductRepositoryImpl
import com.aigs.base.data.repository.SettingsRepositoryImpl
import com.aigs.base.ui.screens.createaccount.CreateAccountViewModel
import com.aigs.base.ui.screens.home.HomeViewModel
import com.aigs.base.ui.screens.login.LoginViewModel
import com.aigs.base.ui.screens.onboarding.OnboardingViewModel
import com.aigs.base.ui.screens.settings.SettingsViewModel
import com.aigs.base.ui.screens.splash.SplashViewModel
import com.aigs.base.BuildConfig
import com.aigs.base.domain.repository.AuthRepository
import com.aigs.base.domain.repository.CountryRepository
import com.aigs.base.domain.repository.ProductRepository
import com.aigs.base.domain.repository.SettingsRepository
import com.aigs.base.domain.usecase.GetCategoriesUseCase
import com.aigs.base.domain.usecase.GetCurrentLanguageUseCase
import com.aigs.base.domain.usecase.GetLanguageUseCase
import com.aigs.base.domain.usecase.GetProductUseCase
import com.aigs.base.domain.usecase.LoginUseCase
import com.aigs.base.domain.usecase.SetLanguageUseCase
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
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsService::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }

    single<CountryRepository> { CountryRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    factory { LogoutUseCase(get()) }
    factory { LoginUseCase(get()) }
    factory { GetProductUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }
    factory { GetCurrentLanguageUseCase(get()) }
    factory { GetLanguageUseCase(get()) }
    factory { SetLanguageUseCase(get()) }

    viewModel { SplashViewModel(get()) }
    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { CreateAccountViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
}