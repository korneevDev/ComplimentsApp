package com.mik0war.complimentsApp.core.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.State

interface SingleItemViewModel {
    fun getItem()
    fun changeItemStatus()
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}