package com.mik0war.complimentsApp.core.presentation

interface Show<T>{
    fun show(arg : T)
}
interface ShowText : Show<String>
interface ShowProgress : Show<Boolean>
interface ShowImage : Show<Int>{
    fun visibility(isVisible: Boolean)
}
interface EnableButton{
    fun enable(isEnable: Boolean)
}