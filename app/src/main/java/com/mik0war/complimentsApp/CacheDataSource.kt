package com.mik0war.complimentsApp

interface CacheDataSource {
    fun getCompliment(complimentCallBack: ComplimentCacheCallBack)
    fun addOrRemove(id : String, compliment: Compliment) : ComplimentUIModel
}

interface ComplimentCacheCallBack{
    fun provide(compliment: Compliment)
    fun fail()
}