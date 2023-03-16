package com.mik0war.complimentsApp.core.presentation.viewmodel

interface CommonViewModel : SingleItemViewModel, ListViewModel, PersistentDataViewModel {
    fun changeDataSource(isCached: Boolean)
}