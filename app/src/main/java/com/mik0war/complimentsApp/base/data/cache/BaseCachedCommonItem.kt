package com.mik0war.complimentsApp.base.data.cache

import com.mik0war.complimentsApp.core.data.cache.CachedCommonItem
import com.mik0war.complimentsApp.core.data.ChangeCommonItem
import com.mik0war.complimentsApp.core.data.ChangeStatus
import com.mik0war.complimentsApp.base.data.CommonDataModel


class BaseCachedCommonItem : CachedCommonItem {
    private var cached : ChangeCommonItem = ChangeCommonItem.Empty()

    override fun save(commonDataModel: CommonDataModel) {
        cached = commonDataModel
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
        return cached.change(cacheDataSource)
    }

}