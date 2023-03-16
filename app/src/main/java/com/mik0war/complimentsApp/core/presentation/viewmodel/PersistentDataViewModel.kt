package com.mik0war.complimentsApp.core.presentation.viewmodel

interface PersistentDataViewModel {
    fun loadIsFavoritesState(): Boolean
    fun saveIsFavoritesState()
}