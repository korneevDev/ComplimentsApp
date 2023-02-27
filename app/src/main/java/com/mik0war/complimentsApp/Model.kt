package com.mik0war.complimentsApp

interface Model {
    fun getCompliment()

    fun init(callBack: ResultCallBack)

    fun clear()

}

interface ResultCallBack{
    fun provideSuccess(data: Compliment)
    fun provideError(error: JokeError)
}