package com.mik0war.complimentsApp.data.cache

import com.mik0war.complimentsApp.core.data.cache.CachedCommonItem
import com.mik0war.complimentsApp.core.data.ChangeCommonItem
import com.mik0war.complimentsApp.core.data.ChangeStatus
import com.mik0war.complimentsApp.data.CommonDataModel


class BaseCachedCommonItem : CachedCommonItem {
    private var cached : ChangeCommonItem = ChangeCommonItem.Empty()

    override fun save(commonDataModel: CommonDataModel) {
        cached = commonDataModel
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override fun checkIsCached(id: String) = cached.checkIsCached(id)

    override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
        return cached.change(cacheDataSource)
    }

}