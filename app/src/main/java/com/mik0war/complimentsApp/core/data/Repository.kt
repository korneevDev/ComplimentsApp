package com.mik0war.complimentsApp.core.data

interface Repository : SingleItemRepository, ListRepository, PersistentDataRepository {
    fun chooseDataSource(isCache : Boolean)
}

