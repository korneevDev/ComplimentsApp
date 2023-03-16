package com.mik0war.complimentsApp.core.data.cache

interface PersistentDataSource {
    fun save(data: Boolean, name: String)
    fun load(name: String): Boolean
}