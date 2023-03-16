package com.mik0war.complimentsApp.core.domain

interface PersistentDataInteractor {
    fun loadIsFavorites(name: String): Boolean
    fun saveIsFavorites(name: String)
}