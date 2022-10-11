package com.miladsh7.mytodolist.utils

import android.content.Context
import java.util.*

internal object LocaleManager {

    fun setLocale(context: Context): Context {
        return updateResources(context)
    }

    private fun updateResources(context: Context, locale: Locale = Locale("fa")): Context {
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }
}