package com.mik0war.complimentsApp.presentation

abstract class BaseModule<T : BaseViewModel> {
    abstract fun getViewModel() : T
    abstract fun getCommunication() : BaseCommunication
}