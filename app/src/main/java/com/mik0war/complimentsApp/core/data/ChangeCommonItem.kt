package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.base.data.CommonDataModel

interface ChangeCommonItem {
    suspend fun change(cacheDataSource: ChangeStatus) : CommonDataModel

    class Empty : ChangeCommonItem {
        override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change common item called")
        }
    }
}