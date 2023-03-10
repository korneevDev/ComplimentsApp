package com.mik0war.complimentsApp.presentation

import androidx.lifecycle.*
import com.mik0war.complimentsApp.domain.ComplimentInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BaseViewModel(
    private val interactor: ComplimentInteractor,
    private val communication: Communication,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    fun getCompliment() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        interactor.getCompliment().to().getData(communication)
    }

    fun changeComplimentStatus() = viewModelScope.launch(dispatcher) {
        if(communication.isState(State.INITIAL))
            interactor.changeFavorites().to().getData(communication)
    }

    fun changeDataSource(isCached: Boolean) {
        interactor.getFavoriteCompliments(isCached)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }
}