package com.mik0war.complimentsApp.base.presentation

import androidx.lifecycle.*
import com.mik0war.complimentsApp.core.presentation.CommonViewModel
import com.mik0war.complimentsApp.core.presentation.Communication
import com.mik0war.complimentsApp.core.domain.CommonInteractor
import kotlinx.coroutines.*

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