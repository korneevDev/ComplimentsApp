package com.mik0war.complimentsApp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedCompliment: CachedCompliment

    ) : Repository {

    private var currentDataSource : ComplimentDataFetcher = cloudDataSource

    override suspend fun getCompliment() : ComplimentDataModel = withContext(Dispatchers.IO){
        try{
            val compliment = currentDataSource.getCompliment()
            cachedCompliment.save(compliment)
            return@withContext compliment
        } catch (e : Exception){
            cachedCompliment.clear()
            throw e
        }
    }
    override suspend fun changeComplimentStatus(): ComplimentDataModel = cachedCompliment.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        currentDataSource = if(isCache) cacheDataSource else cloudDataSource
    }
}