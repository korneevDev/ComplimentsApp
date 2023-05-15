package com.mik0war.complimentsApp.core.domain.interactor

interface PersistentDataInteractor {
    fun loadIsFavorites(name: String): Boolean
    fun saveIsFavorites(name: String)
}