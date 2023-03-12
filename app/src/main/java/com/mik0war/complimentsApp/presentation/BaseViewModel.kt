package com.mik0war.complimentsApp.presentation

import androidx.lifecycle.*
import com.mik0war.complimentsApp.domain.CommonInteractor
import kotlinx.coroutines.*

interface CommonViewModel{
    fun getItem()
    fun changeItemStatus()
    fun changeDataSource(isCached: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
}

class BaseViewModel(
    private val interactor: CommonInteractor,
    private val communication: Communication,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel {
    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().getData(communication)
    }}

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if(communication.isState(State.INITIAL))
                interactor.changeFavorites().to().getData(communication)
            }
    }

    override fun changeDataSource(isCached: Boolean) {
        interactor.getFavoriteItems(isCached)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }
}