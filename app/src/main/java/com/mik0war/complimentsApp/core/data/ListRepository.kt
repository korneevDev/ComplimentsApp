package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.data.CommonDataModel

interface ListRepository {
    suspend fun getCommonItemList() : List<CommonDataModel>
    suspend fun removeItem(id: String)
    suspend fun checkIsCached(id: String) : Boolean
}