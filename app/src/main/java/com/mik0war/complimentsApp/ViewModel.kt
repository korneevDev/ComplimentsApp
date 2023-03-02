package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel(){
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

    fun getJoke() = viewModelScope.launch{
        val uiModel = model.getCompliment()
        callBack?.let {
            uiModel.map(it)
        }
    }

    fun changeComplimentStatus() = viewModelScope.launch{
        val uiModel = model.changeComplimentStatus()
        callBack?.let {
            uiModel?.map(it)
        }
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