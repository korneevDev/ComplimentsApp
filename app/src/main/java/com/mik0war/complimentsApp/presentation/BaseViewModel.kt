package com.mik0war.complimentsApp.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mik0war.complimentsApp.core.domain.CommonInteractor
import com.mik0war.complimentsApp.core.presentation.CommonCommunication
import com.mik0war.complimentsApp.core.presentation.CommonViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel(
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
                getItemList()
            }
    }

    override fun changeItemStatus(id: String){
        viewModelScope.launch(dispatcher) {
            if(interactor.checkId(id))
                changeItemStatus()
            else {
                interactor.removeItem(id)
                getItemList()
            }
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

    fun getCommunication() = communication
}

class ComplimentViewMode(interactor: CommonInteractor, communication: CommonCommunication)
    : BaseViewModel(interactor, communication)

class QuoteViewMode(interactor: CommonInteractor, communication: CommonCommunication)
    : BaseViewModel(interactor, communication)