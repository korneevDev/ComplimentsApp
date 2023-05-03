package com.mik0war.complimentsApp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.mik0war.complimentsApp.core.presentation.CommonCommunication
import com.mik0war.complimentsApp.presentation.recyclerView.CommonDiffUtilCallback

class BaseCommunication : CommonCommunication {
    private val liveData = MutableLiveData<State>()
    private val listLiveData = MutableLiveData<ArrayList<CommonUIModel>>()
    private lateinit var diffResult: DiffUtil.DiffResult

    override fun showState(state: State) {
        liveData.value = state
    }

    override fun showDataList(list: List<CommonUIModel>) {
        val callback = CommonDiffUtilCallback(listLiveData.value ?: emptyList(), list)
        diffResult = DiffUtil.calculateDiff(callback)
        listLiveData.value = ArrayList(list)
    }

    override fun getDiffResult(): DiffUtil.DiffResult = diffResult

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        liveData.observe(owner, observer)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>) {
        listLiveData.observe(owner, observer)
    }

    override fun isState(type: Int): Boolean = liveData.value?.isType(type) ?: false
    override fun getList(): List<CommonUIModel> = listLiveData.value ?: emptyList()
}