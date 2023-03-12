package com.mik0war.complimentsApp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.State

interface CommonViewModel{
    fun getItem()
    fun changeItemStatus()
    fun changeDataSource(isCached: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}