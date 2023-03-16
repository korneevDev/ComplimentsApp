package com.mik0war.complimentsApp.data.cache

import android.content.Context
import android.content.SharedPreferences
import com.mik0war.complimentsApp.core.data.cache.PersistentDataSource

class BasePersistentDataSource(private val context: Context) : PersistentDataSource {
    private val sharedPreferences: SharedPreferences? = null

    override fun save(data: Boolean, name: String) {
        getSharePreferences(name).edit().putBoolean(IS_FAVORITES_KEY, data).apply()
    }

    override fun load(name: String): Boolean = getSharePreferences(name).getBoolean(IS_FAVORITES_KEY, false)

    private fun getSharePreferences(name: String) = sharedPreferences ?: context.getSharedPreferences(
        name, Context.MODE_PRIVATE)

    private companion object{
        const val IS_FAVORITES_KEY = "IS_FAVORITES"
    }
}