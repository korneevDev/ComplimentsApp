package com.mik0war.complimentsApp.core.data

import com.mik0war.complimentsApp.data.CommonDataModel

interface ChangeCommonItem {
    suspend fun change(cacheDataSource: ChangeStatus) : CommonDataModel
    fun checkIsCached(id: String): Boolean

    class Empty : ChangeCommonItem {
        override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change common item called")
        }

        override fun checkIsCached(id: String) = id.isEmpty()
    }
}