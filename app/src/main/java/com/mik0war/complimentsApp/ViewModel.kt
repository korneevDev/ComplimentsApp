package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {
    private var callBack : DataProvider? = null

    fun init(callBack : DataProvider){
        this.callBack = callBack

        model.init(object : ResultCallBack{
            override fun provideCompliment(compliment: Compliment) = compliment.map(callBack)
        })

    }

    fun getJoke(){
        model.getCompliment()
    }

    fun clear(){
        model.clear()
    }
}

interface DataProvider{
    fun provideText(text: String)

    fun provideIconId(@DrawableRes id : Int)
}