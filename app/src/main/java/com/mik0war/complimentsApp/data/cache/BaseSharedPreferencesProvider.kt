package com.mik0war.complimentsApp.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.mik0war.complimentsApp.core.data.cache.SharedPreferencesProvider

class BaseSharedPreferencesProvider(private val context: Context) : SharedPreferencesProvider {
    private val sharedPreferences: SharedPreferences? = null

    override fun getSharedPreferences(name: String): SharedPreferences
        = sharedPreferences ?: context.getSharedPreferences(
            name, Context.MODE_PRIVATE)
}