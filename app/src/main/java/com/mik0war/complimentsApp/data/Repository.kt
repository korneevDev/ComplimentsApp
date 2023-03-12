package com.mik0war.complimentsApp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface Repository {
    suspend fun getCommonItem() : CommonDataModel
    suspend fun changeStatus() : CommonDataModel
    fun chooseDataSource(isCache : Boolean)
}

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedCommonItem: CachedCommonItem

) : Repository {

    private var currentDataSource : DataFetcher = cloudDataSource

    override suspend fun getCommonItem() : CommonDataModel = withContext(Dispatchers.IO){
        try{
            val data = currentDataSource.getData()
            cachedCommonItem.save(data)
            return@withContext data
        } catch (e : Exception){
            cachedCommonItem.clear()
            throw e
        }
    }
    override suspend fun changeStatus(): CommonDataModel = cachedCommonItem.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        currentDataSource = if(isCache) cacheDataSource else cloudDataSource
    }
}