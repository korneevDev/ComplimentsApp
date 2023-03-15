package com.mik0war.complimentsApp

import com.mik0war.complimentsApp.presentation.BaseCommunication
import com.mik0war.complimentsApp.presentation.BaseViewModel

abstract class BaseModule<T : BaseViewModel> {
    abstract fun getViewModel() : T
    abstract fun getCommunication() : BaseCommunication
}