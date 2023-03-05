package com.mik0war.complimentsApp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val resourceManager: ResourceManager
    ) : Model {
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy {ServiceUnavailable(resourceManager)}
    private val noFavoriteCompliments by lazy {NoFavoriteCompliments(resourceManager)}

    private var cachedCompliment : Compliment? = null

    private var isGetFromCache = false

    override suspend fun getCompliment() : ComplimentUIModel = withContext(Dispatchers.IO){
        val resultHandler = if (isGetFromCache)
            CacheResultHandler(cacheDataSource)
        else CloudResultHandler(cloudDataSource)

        return@withContext resultHandler.process()
    }
    override suspend fun changeComplimentStatus(): ComplimentUIModel? = cachedCompliment?.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        isGetFromCache = isCache
    }

    private interface ResultHandler<T, S> {
        fun handleResult(result : Result<T, S>) : ComplimentUIModel
    }

    private abstract inner class BaseResultHandler<E, R>(
        private val complimentDataFetcher: ComplimentDataFetcher<E, R>
    ) : ResultHandler<E, R>{
        suspend fun process(): ComplimentUIModel = handleResult(complimentDataFetcher.getCompliment())
    }

    private inner class CloudResultHandler(
        complimentDataFetcher: ComplimentDataFetcher<ComplimentServerModel, ErrorType>) :
        BaseResultHandler<ComplimentServerModel, ErrorType>(complimentDataFetcher) {
        override fun handleResult(result: Result<ComplimentServerModel, ErrorType>): ComplimentUIModel =
            when (result) {
            is Result.Success -> {
                result.data.toCompliment().let {
                    cachedCompliment = it
                    it.toBaseCompliment()
                }
            }
            is Result.Error -> {
                cachedCompliment = null
                val type = if (result.errorType == ErrorType.NO_CONNECTION)
                    noConnection else serviceUnavailable
                FailedComplimentUIModel(type.getErrorMessage())
            }
        }
    }

    private inner class CacheResultHandler(
        complimentDataFetcher: ComplimentDataFetcher<Compliment, Unit>) :
        BaseResultHandler<Compliment, Unit>(complimentDataFetcher) {
        override fun handleResult(result: Result<Compliment, Unit>): ComplimentUIModel =
            when(result) {
                is Result.Success -> {
                    result.data.let {
                        cachedCompliment = it
                        it.toFavoriteCompliment()
                    }
                }
                is Result.Error -> {
                    cachedCompliment = null
                    FailedComplimentUIModel(noFavoriteCompliments.getErrorMessage())
                }
            }
    }
}