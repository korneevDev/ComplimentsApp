package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {
    private var callBack : DataProvider? = null

    private val complimentCallBack = object : ComplimentCallBack {
            override fun provideCompliment(compliment: ComplimentUIModel) {
                callBack?.let {
                    compliment.map(it)
                }
            }
    }

    fun init(callBack : DataProvider){
        this.callBack = callBack

        model.init(complimentCallBack)

    }

    fun getJoke(){
        model.getCompliment()
    }

    fun changeComplimentStatus(){
        model.changeComplimentStatus(complimentCallBack)
    }

    fun changeDataSource(isCached : Boolean){
        model.chooseDataSource(isCached)
    }

    fun clear(){
        model.clear()
    }
}

interface DataProvider{
    fun provideText(text: String)

    fun provideIconId(@DrawableRes id : Int)
}