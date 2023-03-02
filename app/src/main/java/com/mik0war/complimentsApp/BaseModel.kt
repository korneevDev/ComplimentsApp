package com.mik0war.complimentsApp

class BaseModel(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val resourceManager: ResourceManager
    ) : Model {
    private var complimentCallBack : ComplimentCallBack? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy {ServiceUnavailable(resourceManager)}
    private val noFavoriteCompliments by lazy {NoFavoriteCompliments(resourceManager)}

    private var cachedCompliment : Compliment? = null

    private var isGetFromCache = false

    override suspend fun getCompliment() : ComplimentUIModel{
        if(isGetFromCache){
            return when(val result = cacheDataSource.getCompliment()) {
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

        } else return  when (val result = cloudDataSource.getCompliment()) {
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

    override fun init(callBack: ComplimentCallBack) {
        this.complimentCallBack = callBack
    }

    override suspend fun changeComplimentStatus(): ComplimentUIModel? = cachedCompliment?.change(cacheDataSource)

    override fun chooseDataSource(isCache: Boolean) {
        isGetFromCache = isCache
    }

    override fun clear() {
        this.complimentCallBack = null
    }
}