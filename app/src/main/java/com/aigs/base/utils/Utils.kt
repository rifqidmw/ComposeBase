package com.aigs.base.utils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import java.util.Locale

object Utils {
    fun appLog(tag: String, message: String) {
        Log.i(tag, message)
    }

    fun setLocale(context: Context, code: String): Context {
        return if (code != "en") {
            val locale = Locale(code)
            Locale.setDefault(locale)

            val config = Configuration(context.resources.configuration)
            config.setLocale(locale)
            context.createConfigurationContext(config)
        } else {
            context
        }
    }

    fun updateLanguage(context: Context, code: String) {
        val locale = when (code) {
            "en" -> Locale.ENGLISH
            else -> Locale(code)
        }
        Locale.setDefault(locale)

        val resources = context.resources
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}