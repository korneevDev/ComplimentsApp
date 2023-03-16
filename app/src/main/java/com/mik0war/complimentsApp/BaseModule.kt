package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.presentation.BaseCommunication
import com.mik0war.complimentsApp.presentation.BaseViewModel

abstract class BaseModule<T : BaseViewModel> {
    private var communication : BaseCommunication? = null
    abstract fun getViewModel() : T
    protected fun getCommunication() : BaseCommunication =
        communication ?: BaseCommunication().also { communication = it }
}