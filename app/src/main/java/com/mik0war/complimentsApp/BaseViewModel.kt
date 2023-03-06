package com.mik0war.complimentsApp

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel(
    private val model: Model,
    private val communication: Communication,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun getCompliment() = viewModelScope.launch(dispatcher) {
        communication.showData(model.getCompliment().getData())
    }

    fun changeComplimentStatus() = viewModelScope.launch(dispatcher) {
        model.changeComplimentStatus()?.let {
            communication.showData(it.getData())
        }
    }

    fun changeDataSource(isCached: Boolean) {
        model.chooseDataSource(isCached)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>){
        communication.observe(owner, observer)
    }
}