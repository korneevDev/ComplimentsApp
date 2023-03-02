package com.mik0war.complimentsApp

interface Model {
    suspend fun getCompliment() : ComplimentUIModel

    fun init(callBack: ComplimentCallBack)

    suspend fun changeComplimentStatus() : ComplimentUIModel?

    fun chooseDataSource(isCache : Boolean)

    fun clear()

}

interface ComplimentCallBack{
    fun provideCompliment(compliment : ComplimentUIModel)
}