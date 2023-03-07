package com.mik0war.complimentsApp

import androidx.annotation.DrawableRes
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
        communication.showState(State.Progress)
        model.getCompliment().getData(communication)
    }

    fun changeComplimentStatus() = viewModelScope.launch(dispatcher) {
        model.changeComplimentStatus()?.getData(communication)
    }

    fun changeDataSource(isCached: Boolean) {
        model.chooseDataSource(isCached)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>){
        communication.observe(owner, observer)
    }
}

sealed class State{
    object Progress : State() {
        override fun show(
            progress: ShowProgress,
            button: EnableButton,
        ) {
            progress.show(true)
            button.enable(false)
        }
    }

    data class Initial(val text: String, @DrawableRes val id: Int) : State() {
        override fun show(
            progress: ShowProgress,
            button: EnableButton,
        ) {
            progress.show(false)
            button.enable(true)
        }

        override fun show(
            textView: ShowText,
            imageButton: ShowImage
        ) {
            textView.show(text)
            imageButton.show(id)
        }
    }

    fun show(
        progress: ShowProgress,
        button: EnableButton,
        textView: ShowText,
        imageButton: ShowImage
        ){
        show(progress, button)
        show(textView, imageButton)
    }

    protected open fun show(
        progress: ShowProgress,
        button: EnableButton
    ) {}

    protected open fun show(
        textView: ShowText,
        imageButton: ShowImage
    ) {}
}