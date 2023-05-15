package com.mik0war.complimentsApp.core.data.repository

interface Repository : SingleItemRepository, ListRepository, PersistentDataRepository {
    fun chooseDataSource(isCache : Boolean)
}

