package com.mik0war.complimentsApp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.CommonUIModel
import com.mik0war.complimentsApp.presentation.State

interface CommonViewModel{
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun changeItemStatus(id: String)
    fun changeDataSource(isCached: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
    fun loadIsFavoritesState(): Boolean
    fun saveIsFavoritesState()
}