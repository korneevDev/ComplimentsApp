package com.mik0war.complimentsApp.core.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.CommonUIModel

interface ListViewModel {
    fun getItemList()
    fun changeItemStatus(id: String)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
}