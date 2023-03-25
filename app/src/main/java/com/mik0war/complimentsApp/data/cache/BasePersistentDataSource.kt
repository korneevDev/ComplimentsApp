package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.cache.PersistentDataSource
import com.mik0war.complimentsApp.core.data.cache.SharedPreferencesProvider

class BasePersistentDataSource(
    private val sharedPreferencesProvider: SharedPreferencesProvider
) : PersistentDataSource {
    override fun save(data: Boolean, name: String) {
        sharedPreferencesProvider.getSharedPreferences(name)
            .edit()
            .putBoolean(IS_FAVORITES_KEY, data)
            .apply()
    }

    override fun load(name: String): Boolean =
        sharedPreferencesProvider.getSharedPreferences(name).getBoolean(IS_FAVORITES_KEY, false)

    private companion object {
        const val IS_FAVORITES_KEY = "IS_FAVORITES"
    }
}