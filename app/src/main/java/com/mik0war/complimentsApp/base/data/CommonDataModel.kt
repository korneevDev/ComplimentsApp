package com.mik0war.complimentsApp.base.data

import com.mik0war.complimentsApp.core.data.ChangeCommonItem
import com.mik0war.complimentsApp.core.data.ChangeStatus
import com.mik0war.complimentsApp.core.data.CommonDataModelMapper

class CommonDataModel(
    private val id: String,
    private val text: String,
    private val cached: Boolean = false
) : ChangeCommonItem {
    override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
        return cacheDataSource.addOrRemove(id, this)
    }

    fun <T> map(mapper : CommonDataModelMapper<T>) : T{
        return mapper.map(id, text, cached)
    }

    fun changeCached(cached : Boolean) : CommonDataModel {
        return CommonDataModel(id, text, cached)
    }
}