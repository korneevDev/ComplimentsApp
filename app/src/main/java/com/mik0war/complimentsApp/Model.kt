package com.mik0war.complimentsApp

interface Model {
    fun getCompliment()

    fun init(callBack: ComplimentCallBack)

    fun changeComplimentStatus(callBack: ComplimentCallBack)

    fun chooseDataSource(isCache : Boolean)

    fun clear()

}

interface ComplimentCallBack{
    fun provideCompliment(compliment : Compliment)
}