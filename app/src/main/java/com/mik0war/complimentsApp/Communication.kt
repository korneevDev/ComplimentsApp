package com.mik0war.complimentsApp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {
    fun showData(data: Pair<String, Int>)
    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>)
}

class BaseCommunication : Communication{
    private val liveData = MutableLiveData<Pair<String, Int>>()

    override fun showData(data: Pair<String, Int>) {
        liveData.value = data
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
        liveData.observe(owner, observer)
    }
}