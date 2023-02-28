package com.mik0war.complimentsApp

interface CacheDataSource {
    fun getCompliment(complimentCallBack: ComplimentCacheCallBack)
    fun addOrRemove(id : String, compliment: ComplimentServerModel) : Compliment
}

interface ComplimentCacheCallBack{
    fun provide(compliment: ComplimentServerModel)
    fun fail()
}