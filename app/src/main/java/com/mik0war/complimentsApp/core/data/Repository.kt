package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.base.data.CommonDataModel

interface Repository {
    suspend fun getCommonItem() : CommonDataModel
    suspend fun changeStatus() : CommonDataModel
    fun chooseDataSource(isCache : Boolean)
}
