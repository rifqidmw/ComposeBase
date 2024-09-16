package com.aigs.base.di

import com.aigs.base.common.AppConstants.Api
import com.aigs.base.data.remote.CountriesService
import com.aigs.base.data.repository.CountryRepository
import com.aigs.base.ui.screens.createaccount.CreateAccountViewModel
import com.aigs.base.ui.screens.login.LoginViewModel
import com.aigs.base.ui.screens.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Api.COUNTRIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesService::class.java)
    }

    single { CountryRepository(get()) }

    viewModel { OnboardingViewModel() }
    viewModel { LoginViewModel() }
    viewModel { CreateAccountViewModel(get()) }
}