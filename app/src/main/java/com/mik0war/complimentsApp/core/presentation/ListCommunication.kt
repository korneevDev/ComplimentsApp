package com.mik0war.complimentsApp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.mik0war.complimentsApp.presentation.CommonUIModel

interface ListCommunication : ListGetter{
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUIModel>>)
    fun showDataList(list: List<CommonUIModel>)
}

interface ListGetter {
    fun getList() : List<CommonUIModel>
    fun getDiffResult() : DiffUtil.DiffResult
}