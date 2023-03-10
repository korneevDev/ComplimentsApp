package com.mik0war.complimentsApp.core.data.cache

import com.mik0war.complimentsApp.core.data.ChangeStatus
import com.mik0war.complimentsApp.core.data.DataFetcher
import com.mik0war.complimentsApp.data.CommonDataModel

interface CacheDataSource : DataFetcher, ChangeStatus{
    suspend fun getDataList() : List<CommonDataModel>
    suspend fun removeItem(id: String)
}