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

    private var cachedComplimentServerModel : ComplimentServerModel? = null

    private var isGetFromCache = false

    override fun getCompliment() {
        if(isGetFromCache){
            cacheDataSource.getCompliment(object : ComplimentCacheCallBack{
                override fun provide(compliment: ComplimentServerModel) {
                    cachedComplimentServerModel = compliment
                    complimentCallBack?.provideCompliment(compliment.toFavoriteCompliment())
                }

                override fun fail() {
                    cachedComplimentServerModel = null
                    complimentCallBack?.provideCompliment(
                        FailedCompliment(noFavoriteCompliments.getErrorMessage())
                    )
                }

            })

        } else{
            cloudDataSource.getCompliment(object : ComplimentCloudCallBack{
                override fun provide(compliment: ComplimentServerModel) {
                    cachedComplimentServerModel = compliment
                    complimentCallBack?.provideCompliment(compliment.toBaseCompliment())
                }

                override fun fail(error: ErrorType) {
                    cachedComplimentServerModel = null
                    complimentCallBack?.provideCompliment(FailedCompliment(
                        if (error == ErrorType.SERVICE_UNAVAILABLE)
                            serviceUnavailable.getErrorMessage()
                        else
                            noConnection.getErrorMessage()))
                }
            })
        }
    }

    override fun init(callBack: ComplimentCallBack) {
        this.complimentCallBack = callBack
    }

    override fun changeComplimentStatus(callBack: ComplimentCallBack) {
        cachedComplimentServerModel?.change(cacheDataSource)?.let {
            callBack.provideCompliment(it)
        }
    }

    override fun chooseDataSource(isCache: Boolean) {
        isGetFromCache = isCache
    }

    override fun clear() {
        this.complimentCallBack = null
    }
}