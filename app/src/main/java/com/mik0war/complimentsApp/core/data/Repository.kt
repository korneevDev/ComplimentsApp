package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.data.CommonDataModel

interface Repository {
    suspend fun getCommonItem() : CommonDataModel
    suspend fun getCommonItemList() : List<CommonDataModel>
    suspend fun changeStatus() : CommonDataModel
    fun chooseDataSource(isCache : Boolean)
    suspend fun removeItem(id: String)
}

