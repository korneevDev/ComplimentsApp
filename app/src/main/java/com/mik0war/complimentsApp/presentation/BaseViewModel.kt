package com.mik0war.complimentsApp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.*
import com.mik0war.complimentsApp.core.presentation.CommonViewModel
import com.mik0war.complimentsApp.core.domain.CommonInteractor
import com.mik0war.complimentsApp.core.presentation.CommonCommunication
import kotlinx.coroutines.*

class BaseViewModel(
    private val interactor: CommonInteractor,
    private val communication: CommonCommunication,
    private val dispatcher : CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel {
    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().getData(communication)
    }
    }

    override fun getItemList() {
        viewModelScope.launch(dispatcher) {
            val list = interactor.getItemList().map{it.to()}
            communication.showDataList(list)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if(communication.isState(State.INITIAL))
                interactor.changeFavorites().to().getData(communication)
                communication.showDataList(interactor.getItemList().map{it.to()})
            }
    }

    override fun changeItemStatus(id: String){
        viewModelScope.launch(dispatcher) {
            interactor.removeItem(id)
            communication.showDataList(interactor.getItemList().map{it.to()})
        }
    }

    override fun changeDataSource(isCached: Boolean) {
        interactor.getFavoriteItems(isCached)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>) {
        communication.observeList(owner, observer)
    }
}