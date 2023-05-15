package com.mik0war.complimentsApp.core.data.repository

interface PersistentDataRepository {
    fun saveIsCachedState(name: String)
    fun loadIsCachedState(name: String): Boolean
}