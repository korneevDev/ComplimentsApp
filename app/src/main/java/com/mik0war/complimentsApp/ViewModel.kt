package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel(){
    private var callBack : DataProvider? = null

    fun init(callBack : DataProvider){
        this.callBack = callBack
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
}

interface DataProvider{
    fun provideText(text: String)

    fun provideIconId(@DrawableRes id : Int)
}