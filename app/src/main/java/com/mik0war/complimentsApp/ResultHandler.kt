package com.mik0war.complimentsApp

abstract class BaseResultHandler<E, R>(
    private val complimentDataFetcher: ComplimentDataFetcher<E, R>
){
    suspend fun process(): ComplimentUIModel = handleResult(complimentDataFetcher.getCompliment())

    abstract fun handleResult(result : Result<E, R>) : ComplimentUIModel
}

class CloudResultHandler(
    private val cachedCompliment: CachedCompliment,
    complimentDataFetcher: ComplimentDataFetcher<ComplimentServerModel, ErrorType>,
    private val noConnection : ComplimentError,
    private val serviceUnavailable : ComplimentError
    ) : BaseResultHandler<ComplimentServerModel, ErrorType>(complimentDataFetcher) {
    override fun handleResult(result: Result<ComplimentServerModel, ErrorType>): ComplimentUIModel =
        when (result) {
            is Result.Success -> {
                result.data.toCompliment().let {
                    cachedCompliment.save(it)
                    it.toBaseCompliment()
                }
            }
            is Result.Error -> {
                cachedCompliment.clear()
                val type = if (result.errorType == ErrorType.NO_CONNECTION)
                    noConnection else serviceUnavailable
                FailedComplimentUIModel(type.getErrorMessage())
            }
        }
}

class CacheResultHandler(
        private val cachedCompliment: CachedCompliment,
        complimentDataFetcher: ComplimentDataFetcher<Compliment, Unit>,
        private val noFavoriteCompliments : ComplimentError
    ) : BaseResultHandler<Compliment, Unit>(complimentDataFetcher) {

    override fun handleResult(result: Result<Compliment, Unit>): ComplimentUIModel =
        when(result) {
            is Result.Success -> {
                result.data.let {
                    cachedCompliment.save(it)
                    it.toFavoriteCompliment()
                }
            }
            is Result.Error -> {
                cachedCompliment.clear()
                FailedComplimentUIModel(noFavoriteCompliments.getErrorMessage())
            }
        }
}