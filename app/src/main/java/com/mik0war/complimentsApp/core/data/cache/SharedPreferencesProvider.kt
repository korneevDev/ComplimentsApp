package com.mik0war.complimentsApp.core.data.cache

import android.content.SharedPreferences

interface SharedPreferencesProvider {
    fun getSharedPreferences(name: String) : SharedPreferences
}