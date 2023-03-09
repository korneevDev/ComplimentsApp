package com.mik0war.complimentsApp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.presentation.State

interface Communication {
    fun showState(state: State)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int) : Boolean
}

class BaseCommunication : Communication{
    private val liveData = MutableLiveData<State>()

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean = liveData.value?.isType(type) ?: false
}