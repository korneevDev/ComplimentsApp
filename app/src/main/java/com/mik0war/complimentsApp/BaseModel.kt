package com.mik0war.complimentsApp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudResultHandler : CloudResultHandler,
    private val cacheResultHandler : CacheResultHandler,
    private val cachedCompliment: CachedCompliment

    ) : Model {

    private var currentResultHandler : BaseResultHandler<*, *> = cloudResultHandler

    override suspend fun getCompliment() : ComplimentUIModel = withContext(Dispatchers.IO){
        return@withContext currentResultHandler.process()
    }
    override suspend fun changeComplimentStatus(): ComplimentUIModel? = cachedCompliment.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        currentResultHandler = if(isCache) cacheResultHandler else cloudResultHandler
    }
}