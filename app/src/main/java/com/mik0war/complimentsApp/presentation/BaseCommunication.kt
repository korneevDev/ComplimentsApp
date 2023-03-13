package com.mik0war.complimentsApp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mik0war.complimentsApp.core.presentation.CommonCommunication

class BaseCommunication : CommonCommunication {
    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<ArrayList<CommonUIModel>>()

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUIModel>) {
        listLiveData.value = ArrayList(list)
    }

    override fun removeItem(id: String) : Int {
        val position = listLiveData.value?.indexOf(
            listLiveData.value?.find {
                it.matches(id)
            }) ?: -1
        listLiveData.value?.removeIf{
            it.matches(id)
        }

        return position
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>) {
        listLiveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean = liveData.value?.isType(type) ?: false
    override fun getList(): List<CommonUIModel> = listLiveData.value ?: emptyList()
}