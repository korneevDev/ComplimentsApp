package com.mik0war.complimentsApp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.CommonUIModel

interface ListCommunication {
    fun getList() : List<CommonUIModel>
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
    fun showDataList(list: List<CommonUIModel>)
    fun removeItem(id: String) : Int
}