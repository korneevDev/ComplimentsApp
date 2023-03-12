package com.mik0war.complimentsApp.data


interface ChangeCommonItem {
    suspend fun change(cacheDataSource: ChangeStatus) : CommonDataModel

    class Empty : ChangeCommonItem {
        override suspend fun change(cacheDataSource: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change common item called")
        }
    }
}
interface CachedCommonItem : ChangeCommonItem {
    fun save(commonDataModel: CommonDataModel)
    fun clear()
}

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