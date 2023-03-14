package com.mik0war.complimentsApp.data

import com.mik0war.complimentsApp.core.data.cache.CachedCommonItem
import com.mik0war.complimentsApp.core.data.cloud.CloudDataSource
import com.mik0war.complimentsApp.core.data.cache.CacheDataSource
import com.mik0war.complimentsApp.core.data.DataFetcher
import com.mik0war.complimentsApp.core.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedCommonItem: CachedCommonItem

) : Repository {

    private var currentDataSource : DataFetcher = cloudDataSource

    override suspend fun getCommonItem() : CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cachedCommonItem.save(data)
            return@withContext data
        } catch (e: Exception) {
            cachedCommonItem.clear()
            throw e
        }
    }

    override suspend fun getCommonItemList(): List<CommonDataModel> = withContext(Dispatchers.IO) {
        cacheDataSource.getDataList()
    }

    override suspend fun changeStatus(): CommonDataModel = cachedCommonItem.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        currentDataSource = if(isCache) cacheDataSource else cloudDataSource
    }

    override suspend fun removeItem(id: String) {
        cacheDataSource.removeItem(id)
    }

    override suspend fun checkIsCached(id: String) = cachedCommonItem.checkIsCached(id)
}